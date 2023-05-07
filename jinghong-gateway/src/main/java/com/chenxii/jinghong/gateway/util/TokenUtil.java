package com.chenxii.jinghong.gateway.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class TokenUtil {

    // token过期时间 2 分钟
    private static final int TOKEN_EXPIRE_TIME = 2 * 60 * 1000;

    private static final String TOKEN_KEY = "1234567890";

    public static String createToken(String uid) {
        log.info("【token生成】uid: {}", uid);
        Date now = new Date();
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("uid", uid);
        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer("Jinghong-Gateway")
                .setSubject(uid)
                .setExpiration(DateUtils.addMilliseconds(now, TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, TOKEN_KEY)
                .compact();
        log.info("【token生成】token: {}", token);
        return token;
    }

    public static Claims parseToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(TOKEN_KEY)
                .parseClaimsJws(token)
                .getBody();
        log.info("【token解析】body: {}", body);
        return body;
    }

    public static void checkToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(TOKEN_KEY)
                .parseClaimsJws(token)
                .getBody();
        Date expiration = body.getExpiration();
        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, body, "token已过期");
        }
    }
}
