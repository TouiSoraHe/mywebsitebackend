
package com.zzy.mywebsitebackend.Service;

import com.google.common.collect.Sets;
import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import com.zzy.mywebsitebackend.Data.JsonObj.ArchiveJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.BlogInfoJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.TagJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.BlogInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BlogInfoService {

    @Autowired
    private BlogInfoMapper mapper;

    @Autowired
    ArchiveService archiveService;

    @Autowired
    TagService tagService;

    /**
     * 该方法不对外暴露,仅供BlogService.insert调用
     * @param blogInfoJsonObj
     * @return
     */
    public int insert(BlogInfoJsonObj blogInfoJsonObj){
        blogInfoJsonObj.setTime(new Date());
        blogInfoJsonObj.setViews(0);
        blogInfoJsonObj.setLastModified(new Date());
        blogInfoJsonObj.setDeleted(false);
        BlogInfo blogInfo = blogInfoJsonObj.toBlogInfo();
        int ret = mapper.insert(blogInfo);
        blogInfoJsonObj.setWithBlogInfo(blogInfo);

        //如果插入的bloginfo中包含了标签信息,则需要将其添加到archive中
        if(blogInfoJsonObj.getTags().length>0){
            List<ArchiveJsonObj> archiveJsonObjs = new ArrayList<>();
            for (TagJsonObj tagJsonObj:blogInfoJsonObj.getTags()){
                ArchiveJsonObj archiveJsonObj = new ArchiveJsonObj();
                archiveJsonObj.setTag_id(tagJsonObj.getId());
                archiveJsonObj.setBlog_info_id(blogInfo.getId());
                archiveJsonObjs.add(archiveJsonObj);
            }
            int archiveSuccessCount = archiveService.insertByList(archiveJsonObjs);
            if(archiveSuccessCount != archiveJsonObjs.size()){
                log.warn("BlogService.insert:插入ArchiveJsonObj数据不一致,成功数:"+archiveSuccessCount+",总数:"+archiveJsonObjs.size());
            }
        }
        List<TagJsonObj> tagJsonObjs = tagService.selectByBlogInfoId(blogInfo.getId());
        blogInfoJsonObj.setTags(tagJsonObjs.toArray(new TagJsonObj[tagJsonObjs.size()]));
        return ret;
    }

    public int updateByPrimaryKey(BlogInfoJsonObj blogInfoJsonObj){
        BlogInfoJsonObj oldBlogInfoJsonObj = selectByPrimaryKey(blogInfoJsonObj.getId());
        if(oldBlogInfoJsonObj == null){
            throw new RuntimeException("严重错误,BlogInfo获取失败,"+blogInfoJsonObj.toString());
        }
        blogInfoJsonObj.setTime(oldBlogInfoJsonObj.getTime());
        blogInfoJsonObj.setViews(oldBlogInfoJsonObj.getViews());
        blogInfoJsonObj.setLastModified(new Date());
        blogInfoJsonObj.setDeleted(oldBlogInfoJsonObj.getDeleted());
        blogInfoJsonObj.setBlogId(oldBlogInfoJsonObj.getBlogId());
        BlogInfo blogInfo = blogInfoJsonObj.toBlogInfo();
        int ret = mapper.updateByPrimaryKey(blogInfo);
        blogInfoJsonObj.setWithBlogInfo(blogInfo);

        //判断标签列表,是否有标签的添加或删除
        Set<TagJsonObj> newTagJsonObjs = new HashSet<>(Arrays.asList(blogInfoJsonObj.getTags()));
        Set<TagJsonObj> oldTagJsonObjs = new HashSet<>(tagService.selectByBlogInfoId(blogInfoJsonObj.getId()));
        Set<TagJsonObj> needAddTagJsonObjs = Sets.difference(newTagJsonObjs,oldTagJsonObjs);
        Set<TagJsonObj> needDeleteTagJsonObjs = Sets.difference(oldTagJsonObjs,newTagJsonObjs);
        //将需要添加的归档信息添加到数据库
        List<ArchiveJsonObj> needAddArchiveJsonObjs = new ArrayList<>();
        for (TagJsonObj tagJsonObj:needAddTagJsonObjs){
            ArchiveJsonObj archiveJsonObj = new ArchiveJsonObj();
            archiveJsonObj.setTag_id(tagJsonObj.getId());
            archiveJsonObj.setBlog_info_id(blogInfo.getId());
            needAddArchiveJsonObjs.add(archiveJsonObj);
        }
        int addSuccessCount = archiveService.insertByList(needAddArchiveJsonObjs);
        if(addSuccessCount != needAddArchiveJsonObjs.size()){
            log.warn("BlogService.updateByPrimaryKey:插入ArchiveJsonObj数据不一致,成功数:"+addSuccessCount+",总数:"+needAddArchiveJsonObjs.size());
        }
        //将需要删除的归档信息从数据库删除
        List<Integer> needDeleteTagIds = new ArrayList<>();
        for (TagJsonObj tagJsonObj:needDeleteTagJsonObjs){
            needDeleteTagIds.add(tagJsonObj.getId());
        }
        int deleteSuccessCount = archiveService.deleteByBlogInfoIDAndTagIDs(blogInfoJsonObj.getId(),needDeleteTagIds);
        if(deleteSuccessCount != needDeleteTagIds.size()){
            log.warn("BlogService.updateByPrimaryKey:删除ArchiveJsonObj数据不一致,成功数:"+deleteSuccessCount+",总数:"+needDeleteTagIds.size());
        }

        List<TagJsonObj> tagJsonObjs = tagService.selectByBlogInfoId(blogInfo.getId());
        blogInfoJsonObj.setTags(tagJsonObjs.toArray(new TagJsonObj[tagJsonObjs.size()]));
        return ret;
    }

    public BlogInfoJsonObj selectByPrimaryKey(Integer var0){
        BlogInfo blogInfo =mapper.selectByPrimaryKey(var0);
        if(blogInfo == null) return null;
        BlogInfoJsonObj blogInfoJsonObj = new BlogInfoJsonObj(blogInfo);
        List<TagJsonObj> tagJsonObjs = tagService.selectByBlogInfoId(blogInfo.getId());
        blogInfoJsonObj.setTags(tagJsonObjs.toArray(new TagJsonObj[tagJsonObjs.size()]));
        return blogInfoJsonObj ;
    }

    public BlogInfoJsonObj selectByBlogID(Integer var0){
        BlogInfo blogInfo =mapper.selectByBlogID(var0);
        if(blogInfo == null) return null;
        BlogInfoJsonObj blogInfoJsonObj = new BlogInfoJsonObj(blogInfo);
        List<TagJsonObj> tagJsonObjs = tagService.selectByBlogInfoId(blogInfo.getId());
        blogInfoJsonObj.setTags(tagJsonObjs.toArray(new TagJsonObj[tagJsonObjs.size()]));
        return blogInfoJsonObj ;
    }

    public int deleteByPrimaryKey(Integer blogInfoId){
        archiveService.deleteByBlogInfoID(blogInfoId);
        return mapper.deleteByPrimaryKey(blogInfoId);
    }

    public List<BlogInfoJsonObj> selectAll(){
        Map<Integer,TagJsonObj> tagJsonObjMap = new HashMap<>();//key:tagId value:tagJsonObject
        for(TagJsonObj tagJsonObj:tagService.selectAll()){
            tagJsonObjMap.put(tagJsonObj.getId(),tagJsonObj);
        }
        Map<Integer,List<TagJsonObj>> blogInfoId2TagJsonObjsMap = new HashMap<>();
        for (ArchiveJsonObj archiveJsonObj:archiveService.selectAll()){
            if(!blogInfoId2TagJsonObjsMap.containsKey(archiveJsonObj.getBlog_info_id())){
                blogInfoId2TagJsonObjsMap.put(archiveJsonObj.getBlog_info_id(),new ArrayList<>());
            }
            blogInfoId2TagJsonObjsMap.get(archiveJsonObj.getBlog_info_id()).add(tagJsonObjMap.get(archiveJsonObj.getTag_id()));
        }
        List<BlogInfo> blogInfos = mapper.selectAll();
        List<BlogInfoJsonObj> blogInfoJsonObjs = new ArrayList<>();
        for (BlogInfo blogInfo:blogInfos){
            BlogInfoJsonObj blogInfoJsonObj = new BlogInfoJsonObj(blogInfo);
            List<TagJsonObj> tagJsonObjs = blogInfoId2TagJsonObjsMap.containsKey(blogInfoJsonObj.getId())?
                    blogInfoId2TagJsonObjsMap.get(blogInfo.getId()):
                    new ArrayList<>();
            blogInfoJsonObj.setTags(tagJsonObjs.toArray(new TagJsonObj[tagJsonObjs.size()]));
            blogInfoJsonObjs.add(blogInfoJsonObj);
        }
        return blogInfoJsonObjs;
    }
}
