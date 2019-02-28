package com.zzy.mywebsitebackend.Data.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BlogInfo {
    private Integer id;

    @NotBlank(message = "title不能为空")
    private String title;

    private Date time;

    private Integer views;

    private Integer words;

    private Integer blogId;

    private Date lastModified;

    private Boolean deleted;

    private String summary;

    private Integer imgId;

    @NotNull(message = "bgImg不能为null")
    private Img bgImg = new Img();

    @NotNull(message = "tags不能为null")
    private List<Tag> tags =new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Img getBgImg() {
        return bgImg;
    }

    public void setBgImg(Img bgImg) {
        this.bgImg = bgImg;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BlogInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", words=" + words +
                ", blogId=" + blogId +
                ", lastModified=" + lastModified +
                ", deleted=" + deleted +
                ", summary='" + summary + '\'' +
                ", imgId=" + imgId +
                ", bgImg=" + bgImg +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogInfo blogInfo = (BlogInfo) o;
        return Objects.equals(id, blogInfo.id) &&
                Objects.equals(title, blogInfo.title) &&
                Objects.equals(time.getTime()/10000, blogInfo.time.getTime()/10000) &&
                Objects.equals(views, blogInfo.views) &&
                Objects.equals(words, blogInfo.words) &&
                Objects.equals(blogId, blogInfo.blogId) &&
                Objects.equals(lastModified.getTime()/10000, blogInfo.lastModified.getTime()/10000) &&
                Objects.equals(deleted, blogInfo.deleted) &&
                Objects.equals(summary, blogInfo.summary) &&
                Objects.equals(imgId, blogInfo.imgId) &&
                Objects.equals(bgImg, blogInfo.bgImg) &&
                Objects.equals(tags, blogInfo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, time, views, words, blogId, lastModified, deleted, summary, imgId, bgImg, tags);
    }
}