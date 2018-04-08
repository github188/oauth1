package com.yitutech.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Map;
import org.springframework.util.Assert;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/8
 */
public class JwtUtils {

    public static String createToken(Header header, Claims claims, String secret) {
        Assert.notNull(header, "header 不能为空");
        Assert.notNull(claims, "claims 不能为空");
        Assert.notNull(secret, "secret 不能为空");

        return Jwts.builder()
            .signWith(SignatureAlgorithm.forName(header.getCompressionAlgorithm()), secret)
            .setHeader((Map<String, Object>) header)
            .setClaims(claims).compact();
    }

    public static boolean verifyToken(String token, String secret){
        Assert.notNull(token, "token 不能为空");
        Assert.notNull(secret, "secret 不能为空");



        return false;
    }

}
