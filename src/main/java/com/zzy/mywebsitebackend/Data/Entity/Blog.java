package com.zzy.mywebsitebackend.Data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class Blog {
    private Integer id;

    private String title;

    private Date time;

    private Integer views;

    private Integer words;

    @Pattern(regexp = "^\\d+(\\|\\d+)*|$")
    private String tags;

    private Date last_modified;

    private Boolean deleted;

    private String content;

    @JsonIgnore
    private byte[] img_url;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
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

    public byte[] getImg_url() {
        return img_url;
    }

    public void setImg_url(byte[] img_url) {
        this.img_url = img_url;
    }
}