package com.zzy.mywebsitebackend.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    /**
     * 过期时间改为从配置文件获取
     */
    private static String accessTokenExpireTime;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey;

    private static String refreshTokenExpireTime;

    public static String getAccessTokenExpireTime() {
        return accessTokenExpireTime;
    }

    @Value("${myWebSite.accessTokenExpireTime}")
    private void setAccessTokenExpireTime(String accessTokenExpireTime) {
        Properties.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static String getEncryptJWTKey() {
        return encryptJWTKey;
    }

    @Value("${myWebSite.encryptJWTKey}")
    private void setEncryptJWTKey(String encryptJWTKey) {
        Properties.encryptJWTKey = encryptJWTKey;
    }

    public static String getRefreshTokenExpireTime() {
        return refreshTokenExpireTime;
    }

    @Value("${myWebSite.refreshTokenExpireTime}")
    private void setRefreshTokenExpireTime(String refreshTokenExpireTime) {
        Properties.refreshTokenExpireTime = refreshTokenExpireTime;
    }
}
