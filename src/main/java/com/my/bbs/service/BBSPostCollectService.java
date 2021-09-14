/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.my.bbs.service;

import com.my.bbs.entity.BBSPost;

import java.util.List;

public interface BBSPostCollectService {

    /**
     * 收藏帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    Boolean addCollectRecord(Long userId, Long postId);

    /**
     * 取消收藏帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    Boolean deleteCollectRecord(Long userId, Long postId);

    /**
     * 验证用户是否收藏了帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    Boolean validUserCollect(Long userId, Long postId);

    /**
     * 获取收藏的帖子列表
     *
     * @param userId
     * @return
     */
    List<BBSPost> getCollectRecordsByUserId(Long userId);
}
