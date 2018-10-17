package com.zzy.mywebsitebackend.Data.POJO;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.Tag;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

public class TagJsonObj {
    private Integer id;

    @NotBlank
    private String tagName;

    private Integer[] blogInfoId;

    private ImgUrl bgImg;

    public TagJsonObj() {
    }

    public TagJsonObj(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTag_name();
        this.blogInfoId = JSON.parseObject(tag.getBlog_info_id(), Integer[].class);
        this.bgImg = JSON.parseObject(tag.getImg_url(),ImgUrl.class);
    }

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setId(this.id);
        tag.setTag_name(this.tagName);
        tag.setBlog_info_id(JSON.toJSONBytes(this.blogInfoId));
        tag.setImg_url(JSON.toJSONBytes(this.bgImg));
        return tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer[] getBlogInfoId() {
        return blogInfoId;
    }

    public void setBlogInfoId(Integer[] blogInfoId) {
        this.blogInfoId = blogInfoId;
    }

    public ImgUrl getBgImg() {
        return bgImg;
    }

    public void setBgImg(ImgUrl bgImg) {
        this.bgImg = bgImg;
    }

    @Override
    public String toString() {
        return "TagJsonObj{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", blogInfoId=" + Arrays.toString(blogInfoId) +
                ", bgImg=" + bgImg +
                '}';
    }
}