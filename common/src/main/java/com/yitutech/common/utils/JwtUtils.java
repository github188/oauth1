package com.yitutech.common.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import java.util.Map;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/8
 */
public class JwtUtils {

    public static String createToken(Map header, Claims claims, String secret) {
        Assert.notNull(header, "header 不能为空");
        Assert.notNull(claims, "claims 不能为空");
        Assert.notNull(secret, "secret 不能为空");

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secret)
            .setHeader((Map<String, Object>) header)
            .setClaims(claims).compact();
    }

    public static boolean verifyToken(String token, String secret) {
        Assert.notNull(token, "token 不能为空");
        Assert.notNull(secret, "secret 不能为空");

        String[] tokens = token.split("\\.");
        if (tokens.length == 3) {
//            eyJhYWEiOiJhYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhYmMiOjEyMywiZXhwIjo2MTQ4MzkzOTIwMH0.mT-2kERhnpYYOgIfVTSK8OXYSCkCL6g9tBM2iueEmso
//            String verifyToken = createToken(Base64Utils.decode(tokens[0].getBytes()),Base64Utils.decode(tokens[1].getBytes()))
            byte[] headerByte = Base64Utils.decode(tokens[0].getBytes());
            String headerstring = new String(headerByte);
            DefaultHeader header = JSONObject.parseObject(headerstring, DefaultHeader.class);

            byte[] claimsByte = Base64Utils.decode(tokens[1].getBytes());
            String claimsString = new String(claimsByte);
            DefaultClaims claims = JSONObject.parseObject(claimsString, DefaultClaims.class);

            String verifyToken = createToken(header,claims,secret);
            return verifyToken.equals(token);
        }

        return false;
    }

}
