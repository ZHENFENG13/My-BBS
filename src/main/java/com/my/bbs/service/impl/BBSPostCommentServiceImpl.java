package com.my.bbs.service.impl;

import com.my.bbs.dao.BBSPostCommentMapper;
import com.my.bbs.dao.BBSPostMapper;
import com.my.bbs.dao.BBSUserMapper;
import com.my.bbs.entity.*;
import com.my.bbs.service.BBSPostCommentService;
import com.my.bbs.util.BeanUtil;
import com.my.bbs.util.PageQueryUtil;
import com.my.bbs.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BBSPostCommentServiceImpl implements BBSPostCommentService {

    @Autowired
    private BBSPostCommentMapper bbsPostCommentMapper;

    @Autowired
    private BBSPostMapper bbsPostMapper;

    @Autowired
    private BBSUserMapper bbsUserMapper;

    @Override
    @Transactional
    public Boolean addPostComment(BBSPostComment postComment) {
        BBSPost bbsPost = bbsPostMapper.selectByPrimaryKey(postComment.getPostId());
        if (bbsPost == null) {
            return false;
        }
        BBSUser bbsUser = bbsUserMapper.selectByPrimaryKey(postComment.getCommentUserId());

        if (bbsUser == null || bbsUser.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return false;
        }
        bbsPost.setPostComments(bbsPost.getPostComments() + 1);

        return bbsPostCommentMapper.insertSelective(postComment) > 0 && bbsPostMapper.updateByPrimaryKeySelective(bbsPost) > 0;
    }

    @Override
    @Transactional
    public Boolean delPostComment(Long commentId, Long userId) {

        BBSPostComment bbsPostComment = bbsPostCommentMapper.selectByPrimaryKey(commentId);
        //评论不存在，不能删除
        if (bbsPostComment == null) {
            return false;
        }

        BBSUser bbsUser = bbsUserMapper.selectByPrimaryKey(userId);

        if (bbsUser == null || bbsUser.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return false;
        }

        BBSPost bbsPost = bbsPostMapper.selectByPrimaryKey(bbsPostComment.getPostId());

        //评论所关联的帖子不存在，不能删除
        if (bbsPost == null) {
            return false;
        }

        Long commentCount = bbsPost.getPostComments() - 1;
        if (commentCount >= 0) {
            bbsPost.setPostComments(commentCount);
        }

        if (userId.equals(bbsPostComment.getCommentUserId()) || userId.equals(bbsPost.getPublishUserId())) {
            //这条评论所关联的user或者这条评论所关联帖子的user都可以删除该评论
            //即评论者和发帖者都可以删帖
            return bbsPostCommentMapper.deleteByPrimaryKey(commentId) > 0 && bbsPostMapper.updateByPrimaryKeySelective(bbsPost) > 0;
        }

        return false;
    }

    @Override
    public PageResult getCommentsByPostId(Long postId, int commentPage) {
        Map params = new HashMap();
        params.put("postId", postId);
        params.put("page", commentPage);
        params.put("limit", 6);//每页展示6条评论
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        //查询评论数据
        int total = bbsPostCommentMapper.getTotalComments(pageUtil);
        List<BBSPostComment> commentList = bbsPostCommentMapper.findCommentList(pageUtil);
        List<BBSCommentListEntity> bbsCommentListEntities = new ArrayList<>();
        //数据格式转换
        if (!CollectionUtils.isEmpty(commentList)) {
            bbsCommentListEntities = BeanUtil.copyList(commentList, BBSCommentListEntity.class);
            //当前评论者的userId
            List<Long> userIds = bbsCommentListEntities.stream().map(BBSCommentListEntity::getCommentUserId).collect(Collectors.toList());
            //被回复评论的评论者userId
            List<Long> parentUserIds = bbsCommentListEntities.stream().map(BBSCommentListEntity::getParentCommentUserId).collect(Collectors.toList());
            //分别查询user数据
            List<BBSUser> bbsUsers = bbsUserMapper.selectByPrimaryKeys(userIds);
            List<BBSUser> parentUsers = bbsUserMapper.selectByPrimaryKeys(parentUserIds);
            if (!CollectionUtils.isEmpty(bbsUsers)) {
                //封装user数据
                Map<Long, BBSUser> bbsUserMap = bbsUsers.stream().collect(Collectors.toMap(BBSUser::getUserId, Function.identity(), (entity1, entity2) -> entity1));
                for (BBSCommentListEntity bbsCommentListEntity : bbsCommentListEntities) {
                    if (bbsUserMap.containsKey(bbsCommentListEntity.getCommentUserId())) {
                        //设置头像字段和昵称字段
                        BBSUser tempUser = bbsUserMap.get(bbsCommentListEntity.getCommentUserId());
                        bbsCommentListEntity.setHeadImgUrl(tempUser.getHeadImgUrl());
                        bbsCommentListEntity.setNickName(tempUser.getNickName());
                    }
                }
            }
            if (!CollectionUtils.isEmpty(parentUsers)) {
                //添加被回复者的信息
                Map<Long, BBSUser> parentUserMap = parentUsers.stream().collect(Collectors.toMap(BBSUser::getUserId, Function.identity(), (entity1, entity2) -> entity1));
                for (BBSCommentListEntity bbsCommentListEntity : bbsCommentListEntities) {
                    if (parentUserMap.containsKey(bbsCommentListEntity.getParentCommentUserId())) {
                        //在评论内容前加上"@xxx "
                        BBSUser tempUser = parentUserMap.get(bbsCommentListEntity.getParentCommentUserId());
                        bbsCommentListEntity.setCommentBody("@" + tempUser.getNickName() + "：" + bbsCommentListEntity.getCommentBody());
                    }
                }
            }


        }
        PageResult pageResult = new PageResult(bbsCommentListEntities, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<RecentCommentListEntity> getRecentCommentListByUserId(Long userId) {
        List<BBSPostComment> recentCommentList = bbsPostCommentMapper.getRecentCommentListByUserId(userId);
        List<RecentCommentListEntity> recentCommentListEntities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(recentCommentList)) {
            recentCommentListEntities = BeanUtil.copyList(recentCommentList, RecentCommentListEntity.class);
            //帖子id
            List<Long> postIds = recentCommentList.stream().map(BBSPostComment::getPostId).collect(Collectors.toList());
            //查询帖子数据
            List<BBSPost> bbsPosts = bbsPostMapper.selectByPrimaryKeys(postIds);
            if (!CollectionUtils.isEmpty(bbsPosts)) {
                //封装帖子数据
                Map<Long, BBSPost> bbsPostMap = bbsPosts.stream().collect(Collectors.toMap(BBSPost::getPostId, Function.identity(), (entity1, entity2) -> entity1));
                for (RecentCommentListEntity recentCommentListEntity : recentCommentListEntities) {
                    if (bbsPostMap.containsKey(recentCommentListEntity.getPostId())) {
                        //设置帖子标题
                        BBSPost bbsPost = bbsPostMap.get(recentCommentListEntity.getPostId());
                        recentCommentListEntity.setPostTitle(bbsPost.getPostTitle());
                    }
                }
            }
        }
        return recentCommentListEntities;
    }
}
