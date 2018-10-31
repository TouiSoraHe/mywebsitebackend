package com.zzy.mywebsitebackend.Data.JsonObj;

import com.zzy.mywebsitebackend.Data.Entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserJsonObj {
    private String id;

    @NotBlank
    private String user_name;

    @Email
    private String email;

    private String avatar;

    public UserJsonObj() {
    }

    public UserJsonObj(User user) {
        this.id = user.getId();
        this.user_name = user.getUser_name();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
    }

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setEmail(this.email);
        user.setAvatar(this.avatar);
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    @Override
    public String toString() {
        return "UserJsonObj{" +
                "id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}