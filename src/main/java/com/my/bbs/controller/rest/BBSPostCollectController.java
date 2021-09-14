package com.my.bbs.controller.rest;

import com.my.bbs.common.Constants;
import com.my.bbs.entity.BBSPost;
import com.my.bbs.entity.BBSUser;
import com.my.bbs.service.BBSPostCategoryService;
import com.my.bbs.service.BBSPostCollectService;
import com.my.bbs.service.BBSPostService;
import com.my.bbs.service.BBSUserService;
import com.my.bbs.util.Result;
import com.my.bbs.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class BBSPostCollectController {

    @Resource
    private BBSPostCollectService bbsPostCollectService;

    @PostMapping("/addCollect/{postId}")
    @ResponseBody
    public Result addCollect(@PathVariable("postId") Long postId,
                             HttpSession httpSession) {
        if (null == postId || postId < 0) {
            return ResultGenerator.genFailResult("postId参数错误");
        }
        BBSUser bbsUser = (BBSUser) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        if (bbsPostCollectService.addCollectRecord(bbsUser.getUserId(), postId)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("请求失败，请检查参数及账号是否有操作权限");
        }
    }

    @PostMapping("/delCollect/{postId}")
    @ResponseBody
    public Result delCollect(@PathVariable("postId") Long postId,
                             HttpSession httpSession) {
        if (null == postId || postId < 0) {
            return ResultGenerator.genFailResult("postId参数错误");
        }
        BBSUser bbsUser = (BBSUser) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        if (bbsPostCollectService.deleteCollectRecord(bbsUser.getUserId(), postId)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("请求失败，请检查参数及账号是否有操作权限");
        }
    }
}
