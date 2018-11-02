package com.zzy.mywebsitebackend.Data.JsonObj;

import com.zzy.mywebsitebackend.Data.Entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class UserJsonObj {

    @Pattern(regexp = "^([a-fA-F0-9]{32})$")
    private String id;

    @NotBlank
    private String userName;

    @Email
    private String email;

    private String avatar;

    public UserJsonObj() {
    }

    public UserJsonObj(User user){
        setWithUser(user);
    }

    public void setWithUser(User user) {
        this.id = user.getId();
        this.userName = user.getUser_name();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
    }

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setUser_name(this.userName);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
                ", user_name='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserJsonObj that = (UserJsonObj) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, email, avatar);
    }
}