package com.zzy.mywebsitebackend.Data.POJO;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Date;

public class BlogInfoJsonObj {
    private Integer id;

    @NotBlank
    private String title;

    private Date time;

    private Integer views = 0;

    private Integer words;

    private TagJsonObj[] tags;

    private Integer blogId;

    private Date lastModified;

    private Boolean deleted = false;

    @NotBlank
    private String summary;

    private ImgUrl bgImg;

    public BlogInfoJsonObj() {
    }

    public BlogInfoJsonObj(BlogInfo blogInfo) {
        this.id = blogInfo.getId();
        this.title = blogInfo.getTitle();
        this.time = blogInfo.getTime();
        this.views = blogInfo.getViews();
        this.words = blogInfo.getWords();
        this.blogId = blogInfo.getBlog_id();
        this.lastModified = blogInfo.getLast_modified();
        this.deleted = blogInfo.getDeleted();
        this.summary = blogInfo.getSummary();
        this.tags = JSON.parseObject(blogInfo.getTags(), TagJsonObj[].class);
        this.bgImg = JSON.parseObject(blogInfo.getImg_url(),ImgUrl.class);
    }

    public BlogInfo toBlogInfo() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(this.id);
        blogInfo.setTitle(this.title);
        blogInfo.setTime(this.time);
        blogInfo.setViews(this.views);
        blogInfo.setWords(this.words);
        blogInfo.setBlog_id(this.blogId);
        blogInfo.setLast_modified(this.lastModified);
        blogInfo.setDeleted(this.deleted);
        blogInfo.setSummary(this.summary);
        blogInfo.setTags(JSON.toJSONBytes(this.tags));
        blogInfo.setImg_url(JSON.toJSONBytes(this.bgImg));
        return blogInfo;
    }

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

    public TagJsonObj[] getTags() {
        return tags;
    }

    public void setTags(TagJsonObj[] tags) {
        this.tags = tags;
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

    public ImgUrl getBgImg() {
        return bgImg;
    }

    public void setBgImg(ImgUrl bgImg) {
        this.bgImg = bgImg;
    }

    @Override
    public String toString() {
        return "BlogInfoJsonObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", words=" + words +
                ", tags=" + Arrays.toString(tags) +
                ", blogId=" + blogId +
                ", lastModified=" + lastModified +
                ", deleted=" + deleted +
                ", summary='" + summary + '\'' +
                ", bgImg=" + bgImg +
                '}';
    }
}