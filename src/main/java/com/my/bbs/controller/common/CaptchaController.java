package com.my.bbs.controller.common;

import com.my.bbs.common.Constants;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 13
 * @qq交流群 719099151
 * @email 2449207463@qq.com
 * @link https://github.com/zhenfeng13/My-BBS
 */
@Controller
public class CaptchaController {

    @GetMapping("/common/captcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/gif");

        // 三个参数分别为宽、高、位数
        GifCaptcha captcha = new GifCaptcha(75, 30,4);

        // 设置类型 数字和字母混合
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //设置字体
        captcha.setCharType(Captcha.FONT_9);

        // 验证码存入session
        httpServletRequest.getSession().setAttribute(Constants.VERIFY_CODE_KEY, captcha.text().toLowerCase());

        // 输出图片流
        captcha.out(httpServletResponse.getOutputStream());
    }
}
