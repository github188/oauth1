package com.yitutech.service.impl;

import com.yitutech.service.OauthService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
@Service
public class OauthServiceImpl implements OauthService {

    /**
     * app注册容器
     */
    private static final Map<String, List<String>> APP_CONTAIN = new HashMap<>(16);

    @Override
    public void loginOutRelateApp(String token) {
        /* TODO */
    }

    @Override
    public void appRegister(String token, List<String> logOutUrl) {
        /// TODO: 2018/4/4  
    }
}
