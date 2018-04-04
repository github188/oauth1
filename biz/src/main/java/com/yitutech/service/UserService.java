package com.yitutech.service;

import com.yitutech.model.User;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
public interface UserService {

    /**
     * 认证中心是否存在用户
     *
     * @param user user信心
     * @return true|false
     */
    boolean isExit(User user);
}
