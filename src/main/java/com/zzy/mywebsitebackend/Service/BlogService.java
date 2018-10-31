
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.JsonObj.BlogInfoJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.BlogJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import com.zzy.mywebsitebackend.Util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BlogService {

    @Autowired
    private BlogMapper mapper;

    @Autowired
    BlogInfoService blogInfoService;

    @Autowired
    TagService tagService;

    @Autowired
    ArchiveService archiveService;

    /**
     * 添加一条新的数据
     *
     * @param blogJsonObj
     * @return 添加成功的数据数量
     */
    public int insert(BlogJsonObj blogJsonObj) {
        setBlogInfoProperty(blogJsonObj);
        Blog blog = blogJsonObj.toBlog();
        int ret = mapper.insert(blog);
        if(ret == 0 ) return 0;
        blogJsonObj.setWithBlog(blog);
        BlogInfoJsonObj blogInfoJsonObj = blogJsonObj.getBlogInfo();
        blogInfoJsonObj.setBlogId(blogJsonObj.getId());
        if(blogInfoService.insert(blogInfoJsonObj) == 0){
            throw new RuntimeException("严重错误,BlogInfo添加失败,"+blogJsonObj.toString());
        }
        return ret;
    }

    private void setBlogInfoProperty(BlogJsonObj blogJsonObj){
        BlogInfoJsonObj blogInfoJsonObj = blogJsonObj.getBlogInfo();
        String plainTextContent = Util.Markdown2PlainText(blogJsonObj.getContent());
        blogInfoJsonObj.setWords(Util.WordCountExcludeBlank(plainTextContent));
        final int summaryWordCount = 100;
        blogInfoJsonObj.setSummary(plainTextContent.length() > summaryWordCount ?
                plainTextContent.substring(0, summaryWordCount) :
                plainTextContent);
    }

    public int updateByPrimaryKey(BlogJsonObj blogJsonObj) {
        setBlogInfoProperty(blogJsonObj);
        Blog blog = blogJsonObj.toBlog();
        int ret = mapper.updateByPrimaryKey(blog);
        if(ret == 0 ) return 0;
        blogJsonObj.setWithBlog(blog);
        BlogInfoJsonObj blogInfoJsonObj = blogJsonObj.getBlogInfo();
        BlogInfoJsonObj oldBlogInfoJsonObj = blogInfoService.selectByBlogID(blogJsonObj.getId());
        if(oldBlogInfoJsonObj == null){
            throw new RuntimeException("严重错误,BlogInfo获取失败,"+blogJsonObj.toString());
        }
        blogInfoJsonObj.setId(oldBlogInfoJsonObj.getId());
        if(blogInfoService.updateByPrimaryKey(blogInfoJsonObj) == 0){
            throw new RuntimeException("严重错误,BlogInfo更新失败,"+blogJsonObj.toString());
        }
        return ret;
    }

    public BlogJsonObj selectByPrimaryKey(Integer blogId) {
        Blog blog = mapper.selectByPrimaryKey(blogId);
        if(blog == null) return null;
        BlogInfoJsonObj blogInfoJsonObj = blogInfoService.selectByBlogID(blog.getId());
        BlogJsonObj blogJsonObj = new BlogJsonObj(blog);
        blogJsonObj.setBlogInfo(blogInfoJsonObj);
        return blogJsonObj;
    }

    public int deleteByPrimaryKey(Integer blogId) {
        BlogInfoJsonObj blogInfoJsonObj = blogInfoService.selectByBlogID(blogId);
        if(blogInfoJsonObj!=null){
            blogInfoService.deleteByPrimaryKey(blogInfoJsonObj.getId());
        }
        return mapper.deleteByPrimaryKey(blogId);
    }

    public List<BlogJsonObj> selectAll() {
        List<Blog> blogs = mapper.selectAll();
        if(blogs.size() ==0 ) return new ArrayList<>();
        Map<Integer,BlogInfoJsonObj> blogId2BlogInfoJsonObjMap = new HashMap<>();
        for(BlogInfoJsonObj blogInfoJsonObj:blogInfoService.selectAll()){
            blogId2BlogInfoJsonObjMap.put(blogInfoJsonObj.getBlogId(),blogInfoJsonObj);
        }
        List<BlogJsonObj> blogJsonObjs = new ArrayList<>();
        for (Blog blog:blogs){
            BlogJsonObj blogJsonObj = new BlogJsonObj(blog);
            blogJsonObj.setBlogInfo(blogId2BlogInfoJsonObjMap.get(blogJsonObj.getId()));
            blogJsonObjs.add(blogJsonObj);
        }
        return blogJsonObjs;
    }

}
