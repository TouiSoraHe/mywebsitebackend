package com.zzy.mywebsitebackend.Data.Entity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Archive {
    private Integer id;

    @NotNull
    private Integer blogInfoId;

    @NotNull
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogInfoId() {
        return blogInfoId;
    }

    public void setBlogInfoId(Integer blogInfoId) {
        this.blogInfoId = blogInfoId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", blogInfoId=").append(blogInfoId);
        sb.append(", tagId=").append(tagId);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archive archive = (Archive) o;
        return Objects.equals(blogInfoId, archive.blogInfoId) &&
                Objects.equals(tagId, archive.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogInfoId, tagId);
    }
}