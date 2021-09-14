package com.my.bbs.dao;

import com.my.bbs.entity.BBSPost;
import com.my.bbs.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSPostMapper {
    int deleteByPrimaryKey(Long postId);

    int insert(BBSPost record);

    int insertSelective(BBSPost record);

    BBSPost selectByPrimaryKey(Long postId);

    List<BBSPost> selectByPrimaryKeys(@Param("postIds")List<Long> postIds);

    int updateByPrimaryKeySelective(BBSPost record);

    int updateByPrimaryKeyWithBLOBs(BBSPost record);

    int updateByPrimaryKey(BBSPost record);

    int getTotalBBSPosts(PageQueryUtil pageUtil);

    List<BBSPost> findBBSPostList(PageQueryUtil pageUtil);

    List<BBSPost> getHotTopicBBSPostList();

    List<BBSPost> getMyBBSPostList(@Param("userId") Long userId,@Param("period") String period);
}