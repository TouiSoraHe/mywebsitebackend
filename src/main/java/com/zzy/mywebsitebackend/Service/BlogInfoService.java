
package com.zzy.mywebsitebackend.Service;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.zzy.mywebsitebackend.Data.Entity.*;
import com.zzy.mywebsitebackend.Data.Mapper.BlogInfoMapper;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogInfoService {

    @Autowired
    private BlogInfoMapper mapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private TagService tagService;

    public int insert(BlogInfo blogInfo){
        blogInfo.setTime(new Date());
        blogInfo.setViews(0);
        blogInfo.setLastModified(new Date());
        if(blogInfo.getDeleted() == null){
            blogInfo.setDeleted(false);
        }

        Img img = blogInfo.getBgImg();
        imgService.insert(img);
        blogInfo.setImgId(img.getId());

        int ret = mapper.insert(blogInfo);

        List<Tag> tags = blogInfo.getTags();
        if(tags.size()!=0){
            tagService.insertAll(tags);
            List<Archive> archives = new ArrayList<>();
            for (Tag tag:tags) {
                Archive archive = new Archive();
                archive.setBlogInfoId(blogInfo.getId());
                archive.setTagId(tag.getId());
                archives.add(archive);
            }
            if(archives.size()>0){
                archiveService.insertAll(archives);
            }
        }

        return ret;
    }

    public BlogInfo selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(Integer id){
        BlogInfo blogInfo = selectByPrimaryKey(id);
        int ret = mapper.deleteByPrimaryKey(id);
        imgService.deleteByPrimaryKey(blogInfo.getImgId());
        blogMapper.deleteByPrimaryKey(blogInfo.getBlogId());
        return ret;
    }

//    public int updateByPrimaryKey(BlogInfo var0){
//        return mapper.updateByPrimaryKey(var0);
//    }

    public int updateByPrimaryKeySelective(BlogInfo blogInfo){
        blogInfo.setTime(null);
        blogInfo.setViews(null);
        blogInfo.setLastModified(new Date());
        blogInfo.setBlogId(null);
        blogInfo.setImgId(null);

        tagService.insertAll(blogInfo.getTags());

        List<Archive> oldArchive = archiveService.selectByBlogInfoId(blogInfo.getId());
        List<Archive> nowArchive = new ArrayList<>();
        for (Tag tag:blogInfo.getTags()) {
            Archive archive = new Archive();
            archive.setBlogInfoId(blogInfo.getId());
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

        if(blogInfo.getBgImg().getSmall() != null || blogInfo.getBgImg().getMedium() != null ||blogInfo.getBgImg().getLarge() != null){
            Img img = imgService.selectByPrimaryKey(selectByPrimaryKey(blogInfo.getId()).getImgId());
            img.setMedium(blogInfo.getBgImg().getMedium());
            img.setLarge(blogInfo.getBgImg().getLarge());
            img.setSmall(blogInfo.getBgImg().getSmall());
            imgService.updateByPrimaryKeySelective(img);
        }

        return mapper.updateByPrimaryKeySelective(blogInfo);
    }

    public List<BlogInfo> selectAll(){
        return mapper.selectAll();
    }

    public List<BlogInfo> selectByLimit(String sort, String order, Integer offset, Integer count) {
        return mapper.selectByLimit(getSort(sort),getOrder(order),offset,count);
    }

    public List<BlogInfo> selectByIds(String sort, String order, Integer[] id) {
        return mapper.selectByIds(getSort(sort),getOrder(order),id);
    }

    private String getSort(String sort){
        if (Strings.isNullOrEmpty(sort)) {
            sort = "time";
        }
        return sort;
    }

    private String getOrder(String order){
        if(Strings.isNullOrEmpty(order)){
            order = "desc";
        }
        else{
            order = order.toLowerCase();
        }
        if (!"desc".equals(order)) {
            order = "asc";
        }
        return order;
    }

    public int selectCount() {
        return mapper.selectCount();
    }

//    public int insertSelective(BlogInfo var0){
//        return mapper.insertSelective(var0);
//    }

}
