package com.zzy.mywebsitebackend.Data.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Blog {
    private Integer id;

    @NotBlank(message = "content不能为空")
    private String content;

    @Valid
    @NotNull(message = "BlogInfo不能为null")
    private BlogInfo blogInfo = new BlogInfo();

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

    public BlogInfo getBlogInfo() {
        return blogInfo;
    }

    public void setBlogInfo(BlogInfo blogInfo) {
        this.blogInfo = blogInfo;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", blogInfo=" + blogInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id) &&
                Objects.equals(content, blog.content) &&
                Objects.equals(blogInfo, blog.blogInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, blogInfo);
    }
}