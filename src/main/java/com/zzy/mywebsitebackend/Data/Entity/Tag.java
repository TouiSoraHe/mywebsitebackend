package com.zzy.mywebsitebackend.Data.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tag {
    private Integer id;

    @NotBlank(message = "标签名不能为空")
    private String tagName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer imgId;

    @NotNull(message = "blogInfoIDs不能为null")
    private List<Integer> blogInfoIDs = new ArrayList<>();

    @NotNull(message = "tagImg不能为null")
    private Img tagImg = new Img();

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
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

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Img getTagImg() {
        return tagImg;
    }

    public void setTagImg(Img tagImg) {
        this.tagImg = tagImg;
    }

    public List<Integer> getBlogInfoIDs() {
        return blogInfoIDs;
    }

    public void setBlogInfoIDs(List<Integer> blogInfoIDs) {
        this.blogInfoIDs = blogInfoIDs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", imgId=" + imgId +
                ", blogInfoIDs=" + blogInfoIDs +
                ", tagImg=" + tagImg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(tagName, tag.tagName) &&
                Objects.equals(imgId, tag.imgId) &&
                Objects.equals(blogInfoIDs, tag.blogInfoIDs) &&
                Objects.equals(tagImg, tag.tagImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName, imgId, blogInfoIDs, tagImg);
    }
}