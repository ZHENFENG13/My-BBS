/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.my.bbs.common;

/**
 * @author 13
 * @qq交流群 719099151
 * @email 2449207463@qq.com
 */
public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    DATA_NOT_EXIST("未查询到记录！"),

    SAME_LOGIN_NAME_EXIST("用户名已存在！"),

    LOGIN_NAME_NULL("请输入登录名！"),

    LOGIN_NAME_NOT_EMAIL("请输入正确的邮箱！"),

    LOGIN_PASSWORD_NULL("请输入密码！"),

    LOGIN_VERIFY_CODE_NULL("请输入验证码！"),

    LOGIN_VERIFY_CODE_ERROR("验证码错误！"),

    LOGIN_ERROR("登录失败！"),

    DB_ERROR("database error");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
