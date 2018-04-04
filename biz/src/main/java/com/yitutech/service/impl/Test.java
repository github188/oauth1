package com.yitutech.service.impl;

import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
@Service
public class Test {

    public void test(Map httpSession,String name){
        httpSession.remove(name);
    }
}
