package com.yitutech.service.impl;

import com.yitutech.model.User;
import com.yitutech.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean isExit(User user) {
        return false;
    }
}
