package com.zzy.mywebsitebackend.Data.JsonObj;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BlogJsonObj {
    private Integer id;

    @NotBlank(message = "content不能为空白")
    private String content = "";

    @Valid
    @NotNull(message = "BlogInfo不能为空")
    private BlogInfoJsonObj blogInfo;

    @Autowired
    BlogInfoService blogInfoService;

    public BlogJsonObj() {
    }

    public BlogJsonObj(Blog blog) {
        setWithBlog(blog);
    }

    public void setWithBlog(Blog blog) {
        this.id = blog.getId();
        this.content = blog.getContent();
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setId(this.id);
        blog.setContent(this.content);
        return blog;
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
        this.content = content;
    }

    public BlogInfoJsonObj getBlogInfo() {
        return blogInfo;
    }

    public void setBlogInfo(BlogInfoJsonObj blogInfo) {
        this.blogInfo = blogInfo;
    }

    @Override
    public String toString() {
        return "BlogJsonObj{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", blogInfoJsonObj=" + blogInfo +
                '}';
    }
}