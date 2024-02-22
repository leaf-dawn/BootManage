package com.book.util;

import com.book.pojo.po.Reader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 李嘉劲
 */
public abstract class JwtUtil {
    /**
     * 过期时间(以毫秒为单位)
     */
    public static Long EXPIRED_TIME = 12 * 3600 * 1000L;

    /**
     * 私钥
     */
    private static final String KEY = "ljj-234234234234";

    /**
     * 生产token
     * @param user
     * @return
     */
    public static String buildTokenByUser(Reader reader) {
        return Jwts.builder()
                .setSubject(String.valueOf(reader.getRid()))
                .claim("user", reader)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     * @throws Exception
     */
    public static Reader getCurrentUser(String token) {
        return getCurrentUser(getBody(token));
    }

    /**
     * @param token
     * @return
     */
    public static Claims getBody(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @param claims
     * @return
     */
    public static Reader getCurrentUser(Claims claims) {
        return JSONObject.parseObject(JSONObject.toJSONString(claims.get("user")), Reader.class);
    }

    /**
     * 是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    /**
     * 获取签名时的日期
     * @param claims
     * @return
     */
    public static Date getIssuedAt(Claims claims) {
        return claims.getIssuedAt();
    }

}
