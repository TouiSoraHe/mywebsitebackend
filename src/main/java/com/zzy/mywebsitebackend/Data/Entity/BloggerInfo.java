package com.zzy.mywebsitebackend.Data.Entity;

import java.util.HashMap;
import java.util.Map;

public class BloggerInfo {
    private String username;
    private String email;
    private Map<String,String> contactInformation = new HashMap<>();
    private Map<String,String> headImg = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Map<String, String> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Map<String, String> getHeadImg() {
        return headImg;
    }

    public void setHeadImg(Map<String, String> headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "BloggerInfo.json{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contactInformation=" + contactInformation +
                ", headImg=" + headImg +
                '}';
    }
}
