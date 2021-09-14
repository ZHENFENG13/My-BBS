package com.my.bbs.controller.rest;

import com.my.bbs.entity.BBSPostCategory;
import com.my.bbs.service.BBSPostCategoryService;
import com.my.bbs.service.BBSPostService;
import com.my.bbs.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private BBSPostCategoryService bbsPostCategoryService;

    @Resource
    private BBSPostService bbsPostService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String indexPage(HttpServletRequest request,
                            @RequestParam(value = "categoryId", required = false) Long categoryId,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "period", required = false) String period,
                            @RequestParam(value = "orderBy", required = false) String orderBy) {
        List<BBSPostCategory> bbsPostCategories = bbsPostCategoryService.getBBSPostCategories();
        if (CollectionUtils.isEmpty(bbsPostCategories)) {
            return "error/error_404";
        }

        //第一步：将分类数据封装到request域中
        request.setAttribute("bbsPostCategories", bbsPostCategories);

        //第二步：将近期热议的帖子数据封装到request域中
        request.setAttribute("hotTopicBBSPostList", bbsPostService.getHotTopicBBSPostList());

        //封装参数
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", 10);//默认每页10条
        //帖子类别
        if (categoryId != null && categoryId > 0) {
            request.setAttribute("categoryId", categoryId);
            params.put("categoryId", categoryId);
        }
        //搜索关键字
        if (StringUtils.hasLength(keyword)) {
            request.setAttribute("keyword", keyword);
            params.put("keyword", keyword);
        }
        //时间周期 (周榜、月榜、全部)
        if (StringUtils.hasLength(period)) {
            request.setAttribute("period", period);
            params.put("period", period);
        }
        //排序（按照阅读量或者时间排序）
        if (StringUtils.hasLength(orderBy)) {
            request.setAttribute("orderBy", orderBy);
            params.put("orderBy", orderBy);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);

        //第三步：将帖子列表数据封装到request域中
        request.setAttribute("pageResult", bbsPostService.getBBSPostPageForIndex(pageUtil));
        return "index";
    }
}
