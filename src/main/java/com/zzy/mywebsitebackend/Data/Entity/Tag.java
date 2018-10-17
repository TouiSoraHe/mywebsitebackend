package com.zzy.mywebsitebackend.Data.Entity;

public class Tag {
    private Integer id;

    private String tag_name;

    private byte[] img_url;

    private byte[] blog_info_id;

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

    public byte[] getImg_url() {
        return img_url;
    }

    public void setImg_url(byte[] img_url) {
        this.img_url = img_url;
    }

    public byte[] getBlog_info_id() {
        return blog_info_id;
    }

    public void setBlog_info_id(byte[] blog_info_id) {
        this.blog_info_id = blog_info_id;
    }
}