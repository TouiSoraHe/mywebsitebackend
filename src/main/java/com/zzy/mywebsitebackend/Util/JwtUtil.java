package com.zzy.mywebsitebackend.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zzy.mywebsitebackend.Component.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
@Slf4j
public class JwtUtil {

    /***
     * 校验token是否正确
     * @param token Token
     * @param password 密码
     * @return 是否正确
     */
    public static boolean verify(String token,String password) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, "username") + password + Properties.getEncryptJWTKey();
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new RuntimeException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    /***
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new RuntimeException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /***
     * 生成签名
     * @param username 帐号
     * @param password 密码
     * @return 返回加密的Token
     */
    public static String sign(String username, String password) {
        try {
            // 帐号加JWT私钥加密
            String secret = username+ password + Properties.getEncryptJWTKey();
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + Long.parseLong(Properties.getAccessTokenExpireTime()) * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("currentTimeMillis", String.valueOf(System.currentTimeMillis()))
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new RuntimeException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
}
