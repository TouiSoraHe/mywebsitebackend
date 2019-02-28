package com.zzy.mywebsitebackend.Data.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class Comment {
    private Integer id;

    @NotBlank
    private String content;

    @NotNull
    private Integer parentId;

    private Date time;

    @NotNull
    private Integer blogId;

    private String userId;

    @Valid
    @NotNull
    private User user;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", time=" + time +
                ", blogId=" + blogId +
                ", userId='" + userId + '\'' +
                ", user=" + user +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(parentId, comment.parentId) &&
                Objects.equals(time.getTime()/10000, comment.time.getTime()/10000) &&
                Objects.equals(blogId, comment.blogId) &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(deleted, comment.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, parentId, time, blogId, userId, user, deleted);
    }
}