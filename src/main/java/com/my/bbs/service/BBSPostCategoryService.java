/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.my.bbs.service;

import com.my.bbs.entity.BBSPostCategory;

import java.util.List;

public interface BBSPostCategoryService {
    /**
     * 获取分类列表
     *
     * @return
     */
    List<BBSPostCategory> getBBSPostCategories();

    BBSPostCategory selectById(Integer categoryId);
}
