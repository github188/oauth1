package com.yitutech.controller;

import com.alibaba.fastjson.JSONObject;
import com.yitutech.common.result.Result;
import com.yitutech.common.result.ResultFactory;
import com.yitutech.common.utils.JwtUtils;
import com.yitutech.model.User;
import com.yitutech.service.OauthService;
import com.yitutech.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("")
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oauth_url", "xxx");
        jsonObject.put("service_url", "xxx");
//        jsonObject.put("token","sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd");
//        jsonObject.put("expire",3600);
        return ResultFactory.ok(false);
    }

    @RequestMapping("/check/token")
    @ResponseBody
    public Result checkToken(String token) {
        // TODO: 2018/4/4

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd");
        jsonObject.put("expire", 3600);
        jsonObject.put("available", true);
        jsonObject.put("userId", "xxx");
        return ResultFactory.ok(jsonObject);
    }

    @RequestMapping("/account/token")
    @ResponseBody
    public Result login(@RequestBody User user, @RequestParam(required = false) String callBackUrl) {
        // TODO: 2018/4/4
//        Assert.notNull(userName, "userName不能为空");
//        Assert.notNull(passWord, "passWord不能为空");
//        Assert.notNull(callBackUrl, "callBackUrl不能为空");

        User use1r = new User();
//        user.setUserName(userName);
//        user.setPassWord(passWord);
        if (userService.isExit(user)) {
            String sss = Jwts.builder().signWith(SignatureAlgorithm.HS256,"hello world").claim("abc",123).setExpiration(new Date()).compact();

            Claims claims = Jwts.parser()
                .setSigningKey("hello world")//SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
                .parseClaimsJws(sss)//jwt是JWT字符串
                .getBody();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("call_back_url", "xxx");
        jsonObject.put("token", "sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd");
        jsonObject.put("expire", 3600);
        // jsonObject.put("","");

        return ResultFactory.ok(jsonObject);
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

    public static void main(String[] args) {
        Map m = new HashMap(16);
        m.put("aaa","aaa");
        m.put("alg","HS256");
        m.put("typ","JWT");
        String sss = Jwts.builder().signWith(SignatureAlgorithm.HS256,"hello world").setHeader(m).claim("abc",123).setExpiration(new Date(2018,04,9)).compact();

        Claims claims = new DefaultClaims();
        claims.put("abc",1);
        String sss1 = JwtUtils.createToken( m,claims,"hello world");
//        String claims = Jwts.parser()
//            .setSigningKey("hello world")//SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
//            .parseClaimsJws(sss)//jwt是JWT字符串
//            .getSignature();
        byte[] arr = Base64Utils.decode("eyJhYWEiOiJhYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9".getBytes());
        String arrstring = new String(arr);
        System.out.println(arrstring);

        DefaultHeader header = JSONObject.parseObject(arrstring,DefaultHeader.class);

        boolean flag = JwtUtils.verifyToken(sss1,"hello world");
        System.out.println(claims);
    }
}
