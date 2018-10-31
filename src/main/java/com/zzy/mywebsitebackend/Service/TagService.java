
package com.zzy.mywebsitebackend.Service;

import com.google.common.collect.Sets;
import com.zzy.mywebsitebackend.Data.Entity.Tag;
import com.zzy.mywebsitebackend.Data.JsonObj.ArchiveJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.TagJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TagService {
    @Autowired
    private TagMapper mapper;

    @Autowired
    ArchiveService archiveService;

    public int insert(TagJsonObj tagJsonObj) {
        Tag tag = tagJsonObj.toTag();
        int ret = mapper.insert(tag);
        tagJsonObj.setWithTag(tag);

        //判断是否包含bloginfo,如果有则添加到archive中
        if(tagJsonObj.getBlogInfoId().length>0){
            List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
            for (Integer blogInfoId:tagJsonObj.getBlogInfoId()){
                ArchiveJsonObj archiveJsonObj = new ArchiveJsonObj();
                archiveJsonObj.setTag_id(tagJsonObj.getId());
                archiveJsonObj.setBlog_info_id(blogInfoId);
                archiveJsonObjs.add(archiveJsonObj);
            }
            int archiveSuccessCount = archiveService.insertByList(archiveJsonObjs);
            if(archiveSuccessCount != archiveJsonObjs.size()){
                log.warn("TagService.insert:插入ArchiveJsonObj数据不一致,成功数:"+archiveSuccessCount+",总数:"+archiveJsonObjs.size());
            }
        }
        return ret;
    }

    public int updateByPrimaryKey(TagJsonObj tagJsonObj) {
        Tag tag = tagJsonObj.toTag();
        int ret = mapper.updateByPrimaryKey(tag);
        tagJsonObj.setWithTag(tag);

        Set<Integer> newBlogInfoIds = new HashSet<>(Arrays.asList(tagJsonObj.getBlogInfoId()));
        Set<Integer> oldBlogInfoIds = new HashSet<>(Arrays.asList(getBlogInfoIdsByTagId(tagJsonObj.getId())));
        Set<Integer> needAddBlogInfoIds = Sets.difference(newBlogInfoIds,oldBlogInfoIds);
        Set<Integer> needDeleteBlogInfoIds = Sets.difference(oldBlogInfoIds,newBlogInfoIds);
        //将需要添加的归档信息添加到数据库
        List<ArchiveJsonObj> needAddArchiveJsonObjs = new ArrayList<>();
        for (Integer blogInfoId:needAddBlogInfoIds){
            ArchiveJsonObj archiveJsonObj = new ArchiveJsonObj();
            archiveJsonObj.setTag_id(tagJsonObj.getId());
            archiveJsonObj.setBlog_info_id(blogInfoId);
            needAddArchiveJsonObjs.add(archiveJsonObj);
        }
        int addSuccessCount = archiveService.insertByList(needAddArchiveJsonObjs);
        if(addSuccessCount != needAddArchiveJsonObjs.size()){
            log.warn("TagService.updateByPrimaryKey:插入ArchiveJsonObj数据不一致,成功数:"+addSuccessCount+",总数:"+needAddArchiveJsonObjs.size());
        }
        //将需要删除的归档信息从数据库删除
        int deleteSuccessCount = archiveService.deleteByTagIDAndBlogInfoIDs(tagJsonObj.getId(),new ArrayList<>(needDeleteBlogInfoIds));
        if(deleteSuccessCount != needDeleteBlogInfoIds.size()){
            log.warn("TagService.updateByPrimaryKey:删除ArchiveJsonObj数据不一致,成功数:"+deleteSuccessCount+",总数:"+needDeleteBlogInfoIds.size());
        }

        tagJsonObj.setBlogInfoId(getBlogInfoIdsByTagId(tagJsonObj.getId()));
        return ret;
    }

    public TagJsonObj selectByPrimaryKey(Integer var0) {
        Tag tag = mapper.selectByPrimaryKey(var0);
        if (tag == null) return null;
        TagJsonObj tagJsonObj = TagJsonObj.CreateWithTag(tag);
        tagJsonObj.setBlogInfoId(getBlogInfoIdsByTagId(tagJsonObj.getId()));
        return tagJsonObj;
    }

    public List<TagJsonObj> selectByPrimaryKeyList(List<Integer> ids) {
        List<TagJsonObj> tagJsonObjs = new ArrayList<>();
        List<Tag> tags = mapper.selectByPrimaryKeyList(ids);
        if (tags.size() == 0) return new ArrayList<>();
        Map<Integer,Integer[]> tagId2BlogInfoIds = getBlogInfoIdsByTagIds(ids);
        for (Tag tag : tags) {
            TagJsonObj tagJsonObj = TagJsonObj.CreateWithTag(tag);
            if(tagId2BlogInfoIds.containsKey(tagJsonObj.getId())){
                tagJsonObj.setBlogInfoId(tagId2BlogInfoIds.get(tagJsonObj.getId()));
            }
            tagJsonObjs.add(tagJsonObj);
        }
        return tagJsonObjs;
    }

    public List<TagJsonObj> selectByBlogInfoId(Integer blogInfoId) {
        List<ArchiveJsonObj> archiveJsonObjs = archiveService.selectByBlogInfoID(blogInfoId);
        if (archiveJsonObjs.size() == 0) return new ArrayList<>();
        List<Integer> tagIds = new ArrayList<>();
        for (ArchiveJsonObj archiveJsonObj : archiveJsonObjs) {
            tagIds.add(archiveJsonObj.getTag_id());
        }
        return selectByPrimaryKeyList(tagIds);
    }

    public int deleteByPrimaryKey(Integer tagId) {
        archiveService.deleteByTagID(tagId);
        return mapper.deleteByPrimaryKey(tagId);
    }

    public List<TagJsonObj> selectAll() {
        List<TagJsonObj> tagJsonObjs = new ArrayList<>();
        List<Tag> tags = mapper.selectAll();
        if(tags.size() == 0 ) return new ArrayList<>();
        List<Integer> tagIds = new ArrayList<>();
        for (Tag tag:tags){
            tagIds.add(tag.getId());
        }
        Map<Integer,Integer[]> tagId2BlogInfoIds = getBlogInfoIdsByTagIds(tagIds);
        for (Tag tag : tags) {
            TagJsonObj tagJsonObj = TagJsonObj.CreateWithTag(tag);
            if(tagId2BlogInfoIds.containsKey(tagJsonObj.getId())){
                tagJsonObj.setBlogInfoId(tagId2BlogInfoIds.get(tagJsonObj.getId()));
            }
            tagJsonObjs.add(tagJsonObj);
        }
        return tagJsonObjs;
    }

    private Integer[] getBlogInfoIdsByTagId(Integer tagId) {
        List<ArchiveJsonObj> archiveJsonObjs = archiveService.selectByTagID(tagId);
        Integer[] blogInfoIds = new Integer[archiveJsonObjs.size()];
        for (int i = 0; i < archiveJsonObjs.size(); i++) {
            blogInfoIds[i] = archiveJsonObjs.get(i).getBlog_info_id();
        }
        return blogInfoIds;
    }

    private Map<Integer,Integer[]> getBlogInfoIdsByTagIds(List<Integer> tagIds) {
        List<ArchiveJsonObj> archiveJsonObjs = archiveService.selectByTagIDs(tagIds);
        Map<Integer,List<Integer>> tagId2BlogInfoIdsMap = new HashMap<>();
        for (ArchiveJsonObj archiveJsonObj:archiveJsonObjs){
            if(!tagId2BlogInfoIdsMap.containsKey(archiveJsonObj.getTag_id())){
                tagId2BlogInfoIdsMap.put(archiveJsonObj.getTag_id(),new ArrayList<>());
            }
            tagId2BlogInfoIdsMap.get(archiveJsonObj.getTag_id()).add(archiveJsonObj.getBlog_info_id());
        }
        Map<Integer,Integer[]> ret = new HashMap<>();
        for(Integer tagId:tagId2BlogInfoIdsMap.keySet()){
            List<Integer> blogInfoIds = tagId2BlogInfoIdsMap.get(tagId);
            ret.put(tagId,blogInfoIds.toArray(new Integer[blogInfoIds.size()]));
        }
        return ret;
    }
}
