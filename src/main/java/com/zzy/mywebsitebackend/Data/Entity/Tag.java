package com.zzy.mywebsitebackend.Data.Entity;

public class Tag {
    private Integer id;

    private String tag_name;

    private String tag_img;

    private String blog_info_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name == null ? null : tag_name.trim();
    }

    public String getTag_img() {
        return tag_img;
    }

    public void setTag_img(String tag_img) {
        this.tag_img = tag_img == null ? null : tag_img.trim();
    }

    public String getBlog_info_id() {
        return blog_info_id;
    }

    public void setBlog_info_id(String blog_info_id) {
        this.blog_info_id = blog_info_id == null ? null : blog_info_id.trim();
    }
}