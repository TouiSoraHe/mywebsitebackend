package com.zzy.mywebsitebackend.Data.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

    @Pattern(regexp = "^([a-fA-F0-9]{32})$")
    private String id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer avatarImgId;

    @NotNull(message = "avatar不能为null")
    private Img avatar = new Img();

    @Email(message = "邮箱格式不正确")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAvatarImgId() {
        return avatarImgId;
    }

    public void setAvatarImgId(Integer avatarImgId) {
        this.avatarImgId = avatarImgId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Img getAvatar() {
        return avatar;
    }

    public void setAvatar(Img avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", avatarImgId=" + avatarImgId +
                ", avatar=" + avatar +
                ", email='" + email + '\'' +
                '}';
    }
}