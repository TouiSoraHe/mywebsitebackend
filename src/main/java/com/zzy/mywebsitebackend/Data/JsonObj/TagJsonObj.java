package com.zzy.mywebsitebackend.Data.JsonObj;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.Tag;
import com.zzy.mywebsitebackend.Data.POJO.ImgUrl;
import com.zzy.mywebsitebackend.Service.ArchiveService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TagJsonObj {

    private Integer id;

    @NotBlank
    private String tagName;

    @NotNull
    private Integer[] blogInfoId = new Integer[0];

    @NotNull
    private ImgUrl bgImg = new ImgUrl();

    public TagJsonObj() {
    }

    public static TagJsonObj CreateWithTag(Tag tag){
        TagJsonObj tagJsonObj = new TagJsonObj();
        tagJsonObj.setWithTag(tag);
        return tagJsonObj;
    }

    public void setWithTag(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTag_name();
        this.bgImg = tag.getImg_url()==null?null:JSON.parseObject(tag.getImg_url(),ImgUrl.class);
    }

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setId(this.id);
        tag.setTag_name(this.tagName);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagJsonObj that = (TagJsonObj) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}