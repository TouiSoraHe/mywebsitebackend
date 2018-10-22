package com.zzy.mywebsitebackend.Data.POJO;

import com.alibaba.fastjson.JSON;
import com.zzy.mywebsitebackend.Data.Entity.Blog;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Date;

public class BlogJsonObj {
    private Integer id;

    @NotBlank
    private String title;

    private Date time;

    private Integer views = 0;

    private Integer words;

    private TagJsonObj[] tags;

    private Date lastModified;

    private Boolean deleted = false;

    @NotBlank
    private String content;

    private ImgUrl bgImg;

    public BlogJsonObj() {
    }

    public BlogJsonObj(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.time = blog.getTime();
        this.views = blog.getViews();
        this.words = blog.getWords();
        this.lastModified = blog.getLast_modified();
        this.deleted = blog.getDeleted();
        this.content = blog.getContent();
        this.tags = JSON.parseObject(blog.getTags(), TagJsonObj[].class);
        this.bgImg = JSON.parseObject(blog.getImg_url(),ImgUrl.class);
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setId(this.id);
        blog.setTitle(this.title);
        blog.setTime(this.time);
        blog.setViews(this.views);
        blog.setWords(this.words);
        blog.setLast_modified(this.lastModified);
        blog.setDeleted(this.deleted);
        blog.setContent(this.content);
        blog.setTags(JSON.toJSONBytes(this.tags));
        blog.setImg_url(JSON.toJSONBytes(this.bgImg));
        return blog;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public ImgUrl getBgImg() {
        return bgImg;
    }

    public void setBgImg(ImgUrl bgImg) {
        this.bgImg = bgImg;
    }

    @Override
    public String toString() {
        return "BlogJsonObj{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", words=" + words +
                ", tags=" + Arrays.toString(tags) +
                ", lastModified=" + lastModified +
                ", deleted=" + deleted +
                ", content='" + content + '\'' +
                ", bgImg=" + bgImg +
                '}';
    }
}