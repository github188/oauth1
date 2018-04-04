package com.yitutech.controller;

import com.yitutech.common.result.Result;
import com.yitutech.common.result.ResultFactory;
import com.yitutech.service.OauthService;
import com.yitutech.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
@Controller
public class OauthController {

    @Autowired
    private Environment env;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private UserService userService;

    @RequestMapping("/check/status")
    public Result checkLoginStat(HttpServletRequest httpServletRequest, String oauthUrl,
        String callBackUrl) {
        Cookie[] cookies = httpServletRequest.getCookies();
        HttpSession session = httpServletRequest.getSession();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (session.getAttribute(cookie.getValue()) != null) {
                    return ResultFactory.ok(true);
                }
            }
        }
        return ResultFactory.ok(false);
    }

    @RequestMapping("/check/token")
    public Result checkToken(String token, Long appId, String loginOutUrl) {
        // TODO: 2018/4/4  
        return null;
    }

    @RequestMapping("/login")
    public Result login(String userName, String passWord, String callBackUrl) {
        // TODO: 2018/4/4  
        return null;
    }

    @RequestMapping("/login-out")
    public String loginOut(HttpServletRequest httpServletRequest, String token) {

        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(token);
        oauthService.loginOutRelateApp(token);
//        Cookie[] cookies = httpServletRequest.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName() == "token") {
//                oauthService.loginOutRelateApp(cookie.getValue());
//            }
//            break;
//        }
        return "redirect:/login";
    }


}
