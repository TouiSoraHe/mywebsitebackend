package com.zzy.mywebsitebackend.Data.JsonObj;

import com.zzy.mywebsitebackend.Data.Entity.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommentJsonObj {
    private Integer id;

    @NotBlank
    private String content;

    @NotNull
    private Integer parentId;

    private Date time;

    @NotNull
    private Integer blogId;

    @NotNull
    private Integer userId;

    private Boolean deleted = false;

    public CommentJsonObj() {
    }

    public CommentJsonObj(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.parentId = comment.getParent_id();
        this.time = comment.getTime();
        this.blogId = comment.getBlog_id();
        this.userId = comment.getUser_id();
        this.deleted = comment.getDeleted();
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setContent(this.content);
        comment.setParent_id(this.parentId);
        comment.setTime(this.time);
        comment.setBlog_id(this.blogId);
        comment.setUser_id(this.userId);
        comment.setDeleted(this.deleted);
        return comment;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CommentJsonObj{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", time=" + time +
                ", blogId=" + blogId +
                ", userId=" + userId +
                ", deleted=" + deleted +
                '}';
    }
}