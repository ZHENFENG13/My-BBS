package com.my.bbs.service.impl;

import com.my.bbs.dao.BBSPostCategoryMapper;
import com.my.bbs.entity.BBSPostCategory;
import com.my.bbs.service.BBSPostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BBSPostCategoryServiceImpl implements BBSPostCategoryService {

    @Autowired
    private BBSPostCategoryMapper bbsPostCategoryMapper;

    @Override
    public List<BBSPostCategory> getBBSPostCategories() {
        return bbsPostCategoryMapper.getBBSPostCategories();
    }

    @Override
    public BBSPostCategory selectById(Integer categoryId) {
        return bbsPostCategoryMapper.selectByPrimaryKey(categoryId);
    }
}
