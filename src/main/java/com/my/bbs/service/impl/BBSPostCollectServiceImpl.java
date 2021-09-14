package com.my.bbs.service.impl;

import com.my.bbs.dao.BBSPostCollectMapper;
import com.my.bbs.dao.BBSPostMapper;
import com.my.bbs.dao.BBSUserMapper;
import com.my.bbs.entity.BBSPost;
import com.my.bbs.entity.BBSPostCollect;
import com.my.bbs.entity.BBSUser;
import com.my.bbs.service.BBSPostCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BBSPostCollectServiceImpl implements BBSPostCollectService {

    @Autowired
    private BBSPostCollectMapper bbsPostCollectMapper;

    @Autowired
    private BBSPostMapper bbsPostMapper;

    @Autowired
    private BBSUserMapper bbsUserMapper;

    @Override
    @Transactional
    public Boolean addCollectRecord(Long userId, Long postId) {

        BBSPostCollect bbsPostCollect = bbsPostCollectMapper.selectByUserIdAndPostId(userId, postId);

        BBSUser bbsUser = bbsUserMapper.selectByPrimaryKey(userId);

        if (bbsUser == null || bbsUser.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return false;
        }

        if (bbsPostCollect != null) {
            return true;
        } else {
            bbsPostCollect = new BBSPostCollect();
            bbsPostCollect.setPostId(postId);
            bbsPostCollect.setUserId(userId);

            //收藏数量加1
            BBSPost bbsPost = bbsPostMapper.selectByPrimaryKey(postId);

            bbsPost.setPostCollects(bbsPost.getPostCollects() + 1);

            if (bbsPostCollectMapper.insertSelective(bbsPostCollect) > 0 && bbsPostMapper.updateByPrimaryKey(bbsPost) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteCollectRecord(Long userId, Long postId) {

        BBSPostCollect bbsPostCollect = bbsPostCollectMapper.selectByUserIdAndPostId(userId, postId);

        BBSUser bbsUser = bbsUserMapper.selectByPrimaryKey(userId);

        if (bbsUser == null || bbsUser.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return false;
        }

        if (bbsPostCollect == null) {
            return true;
        } else {
            //收藏数量减1
            BBSPost bbsPost = bbsPostMapper.selectByPrimaryKey(postId);

            Long collectCount = bbsPost.getPostCollects() - 1;
            if (collectCount >= 0) {
                bbsPost.setPostCollects(collectCount);
            }

            if (bbsPostCollectMapper.deleteByPrimaryKey(bbsPostCollect.getRecordId()) > 0 && bbsPostMapper.updateByPrimaryKey(bbsPost) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean validUserCollect(Long userId, Long postId) {
        BBSPostCollect bbsPostCollect = bbsPostCollectMapper.selectByUserIdAndPostId(userId, postId);
        if (bbsPostCollect == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<BBSPost> getCollectRecordsByUserId(Long userId) {

        List<BBSPostCollect> bbsPostCollects = bbsPostCollectMapper.listByUserId(userId);

        if (!CollectionUtils.isEmpty(bbsPostCollects)) {
            List<Long> postIds = bbsPostCollects.stream().map(BBSPostCollect::getPostId).collect(Collectors.toList());
            List<BBSPost> bbsPosts = bbsPostMapper.selectByPrimaryKeys(postIds);
            return bbsPosts;
        }

        return null;
    }
}
