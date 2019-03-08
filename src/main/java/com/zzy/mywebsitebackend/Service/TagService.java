
package com.zzy.mywebsitebackend.Service;

import com.google.common.collect.Sets;
import com.zzy.mywebsitebackend.Data.Entity.Archive;
import com.zzy.mywebsitebackend.Data.Entity.Img;
import com.zzy.mywebsitebackend.Data.Entity.Tag;
import com.zzy.mywebsitebackend.Data.Mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TagService {

    @Autowired
    private TagMapper mapper;

    @Autowired
    private ImgService imgService;

    @Autowired
    private ArchiveService archiveService;

    private  static void copyTag(Tag dest,Tag src){
        dest.setId(src.getId());
        dest.setTagName(src.getTagName());
        dest.setImgId(src.getImgId());
        dest.getTagImg().setId(src.getTagImg().getId());
        dest.getTagImg().setSmall(src.getTagImg().getSmall());
        dest.getTagImg().setLarge(src.getTagImg().getLarge());
        dest.getTagImg().setMedium(src.getTagImg().getMedium());
    }

    @Transactional
    public int insert(Tag tag){
        Img img = tag.getTagImg();
        imgService.insert(img);
        tag.setImgId(img.getId());
        int ret = mapper.insert(tag);
        if(ret == 0){
            imgService.deleteByPrimaryKey(tag.getImgId());
            Tag newTag = selectByTagName(Arrays.asList(tag.getTagName())).get(0);
            copyTag(tag,newTag);
        }

        List<Archive> archives = new ArrayList<>();
        for (Integer blogInfoID : tag.getBlogInfoIDs()) {
            Archive archive = new Archive();
            archive.setBlogInfoId(blogInfoID);
            archive.setTagId(tag.getId());
            archives.add(archive);
        }
        if(archives.size()!=0){
            archiveService.insertAll(archives);
        }

        return ret;
    }

    @Transactional
    public int insertAll(List<Tag> tags)
    {
        if(tags.size() == 0 ) return 0;
        List<Img> imgs = new ArrayList<>();
        for (Tag tag:tags) {
            imgs.add(tag.getTagImg());
        }
        imgService.insertAll(imgs);
        for (Tag tag : tags) {
            tag.setImgId(tag.getTagImg().getId());
        }
        int ret = mapper.insertAll(tags);
        List<Tag> newTags = selectByTags(tags);
        for (Tag newTag:newTags) {
            for (Tag tag:tags) {
                if(tag.getTagName().toLowerCase().equals(newTag.getTagName().toLowerCase())){
                    if(!newTag.getImgId().equals(tag.getImgId())){
                        imgService.deleteByPrimaryKey(tag.getImgId());
                    }
                    copyTag(tag,newTag);
                    break;
                }
            }
        }

        List<Archive> archives = new ArrayList<>();
        for (Tag tag:tags) {
            for (Integer blogInfoID : tag.getBlogInfoIDs()) {
                Archive archive = new Archive();
                archive.setBlogInfoId(blogInfoID);
                archive.setTagId(tag.getId());
                archives.add(archive);
            }
        }
        if(archives.size()>0){
            archiveService.insertAll(archives);
        }
        return ret;
    }

    @Transactional
    public Tag selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer id){
        Tag tag = mapper.selectByPrimaryKey(id);
        if(tag == null) return 0;
        int ret = mapper.deleteByPrimaryKey(id);
        imgService.deleteByPrimaryKey(tag.getImgId());
        return ret;
    }

//    @Transactional
//    public int updateByPrimaryKey(Tag var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    @Transactional
    public int updateByPrimaryKeySelective(Tag tag){
        tag.setImgId(null);
        int ret = mapper.updateByPrimaryKeySelective(tag);
        if(tag.getTagImg().getSmall() != null || tag.getTagImg().getMedium() != null ||tag.getTagImg().getLarge() != null){
            Img img = imgService.selectByPrimaryKey(mapper.selectByPrimaryKey(tag.getId()).getImgId());
            img.setMedium(tag.getTagImg().getMedium());
            img.setLarge(tag.getTagImg().getLarge());
            img.setSmall(tag.getTagImg().getSmall());
            imgService.updateByPrimaryKeySelective(img);
        }

        List<Archive> oldArchive = archiveService.selectByTagId(tag.getId());
        List<Archive> nowArchive = new ArrayList<>();
        for (Integer blogInfoID : tag.getBlogInfoIDs()) {
            Archive archive = new Archive();
            archive.setBlogInfoId(blogInfoID);
            archive.setTagId(tag.getId());
            nowArchive.add(archive);
        }
        Set<Archive> nowArchiveSet = new HashSet<>(nowArchive);
        Set<Archive> oldArchiveSet = new HashSet<>(oldArchive);
        Set<Archive> needAddArchiveSet = Sets.difference(nowArchiveSet,oldArchiveSet);
        Set<Archive> needDeleteArchiveSet = Sets.difference(oldArchiveSet,nowArchiveSet);
        if(needAddArchiveSet.size()!=0){
            archiveService.insertAll(new ArrayList<>(needAddArchiveSet));
        }
        if(needDeleteArchiveSet.size()!=0){
            archiveService.deleteAll(new ArrayList<>(needDeleteArchiveSet));
        }

        return ret;
    }

//    @Transactional
//    public int insertSelective(Tag var0){
//        return mapper.insertSelective(var0);
//    }

    @Transactional
    public List<Tag> selectByTagName(List<String> tagName)
    {
        return mapper.selectByTagName(tagName);
    }

    @Transactional
    public List<Tag> selectByTags(List<Tag> tags)
    {
        List<String> tagNames = new ArrayList<>();
        for (Tag tag:tags) {
            tagNames.add(tag.getTagName());
        }
        return selectByTagName(tagNames);
    }

    @Transactional
    public List<Tag> selectAll() {
        return mapper.selectAll();
    }
}
