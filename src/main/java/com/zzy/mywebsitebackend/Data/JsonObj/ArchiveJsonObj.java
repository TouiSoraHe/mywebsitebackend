package com.zzy.mywebsitebackend.Data.JsonObj;

import com.zzy.mywebsitebackend.Data.Entity.Archive;

public class ArchiveJsonObj {
    private Integer id;

    private Integer blog_info_id;

    private Integer tag_id;

    public ArchiveJsonObj() {
    }

    public ArchiveJsonObj(Archive archive) {
        setWithArchive(archive);
    }

    public void setWithArchive(Archive archive) {
        this.id = archive.getId();
        this.blog_info_id = archive.getBlog_info_id();
        this.tag_id = archive.getTag_id();
    }

    public Archive toArchive() {
        Archive archive = new Archive();
        archive.setId(this.id);
        archive.setBlog_info_id(this.blog_info_id);
        archive.setTag_id(this.tag_id);
        return archive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlog_info_id() {
        return blog_info_id;
    }

    public void setBlog_info_id(Integer blog_info_id) {
        this.blog_info_id = blog_info_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    @Override
    public String toString() {
        return "ArchiveJsonObj{" +
                "id=" + id +
                ", blog_info_id=" + blog_info_id +
                ", tag_id=" + tag_id +
                '}';
    }
}