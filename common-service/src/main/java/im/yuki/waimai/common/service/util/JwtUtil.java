package im.yuki.waimai.common.service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * jwt 工具类
 * 2022-05-11 22:09
 * longkun-dev
 */
public class JwtUtil {

    /**
     * token 默认有效期，默认一个小时
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置密钥明文
     */
    public static final String JWT_KEY = "imyukiwaimai";

    /**
     * 创建 token
     *
     * @param id        id
     * @param subject   subject
     * @param ttlMillis ttlMillis
     * @return new token
     */
    public static String createToken(String id, String subject, Long ttlMillis) {
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = JwtUtil.generateKey();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)  // 唯一 id
                .setSubject(subject)  // 主题，可以是 json 数据
                .setIssuer("im.yuki.waimai")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);  // 设置过期时间
        return jwtBuilder.compact();
    }

    /**
     * 生成加密后的密钥
     *
     * @return 加密后的密钥
     */
    public static SecretKey generateKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析 token
     *
     * @param token token
     * @return 解析后的 token 信息
     */
    public static Claims parseToken(String token) {
        SecretKey secretKey = JwtUtil.generateKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
