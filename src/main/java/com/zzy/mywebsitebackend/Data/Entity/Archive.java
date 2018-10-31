package com.zzy.mywebsitebackend.Data.Entity;

public class Archive {
    private Integer id;

    private Integer blog_info_id;

    private Integer tag_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlog_info_id() {
        return blog_info_id;
    }

    public void setBlog_info_id(Integer blog_info_id) {
        this.blog_info_id = blog_info_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }
}