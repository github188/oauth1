package com.yitutech.service;

import java.util.List;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
public interface OauthService {

    /**
     * 登出服务
     *
     * @param token token
     */
    void loginOutRelateApp(String token);

    /**
     * 应用注册服务
     *
     * @param token token
     * @param logOutUrl logOutUrl
     */
    void appRegister(String token, List<String> logOutUrl);
}
