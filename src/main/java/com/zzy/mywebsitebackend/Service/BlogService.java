
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import com.zzy.mywebsitebackend.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogMapper mapper;

    @Autowired
    private BlogInfoService blogInfoService;

    @Transactional
    public int insert(Blog blog){
        int ret = mapper.insert(blog);

        setBlogInfoProperty(blog);
        ret += blogInfoService.insert(blog.getBlogInfo());
        return ret;
    }

    private void setBlogInfoProperty(Blog blog) {
        BlogInfo blogInfo = blog.getBlogInfo();
        String plainTextContent = Util.Markdown2PlainText(blog.getContent());
        blogInfo.setWords(Util.WordCountExcludeBlank(plainTextContent));
        if(StringUtils.isEmpty(blogInfo.getSummary())){
            final int summaryWordCount = 100;
            blogInfo.setSummary(plainTextContent.length() > summaryWordCount ?
                    plainTextContent.substring(0, summaryWordCount) :
                    plainTextContent);
        }
        blogInfo.setBlogId(blog.getId());
    }

    @Transactional
    public Blog selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    @Transactional
    public int deleteByPrimaryKey(Integer id){
        Blog blog = selectByPrimaryKey(id);
        if(blog==null)return 0;
        blogInfoService.deleteByPrimaryKey(blog.getBlogInfo().getId());
        int ret = mapper.deleteByPrimaryKey(id);
        return ret;
    }

    /**
     * 该方法会修改Blog的部分属性值为null
     * @param blog
     * @return
     */
    @Transactional
    public int updateByPrimaryKeySelective(Blog blog){
        setBlogInfoProperty(blog);
        blog.getBlogInfo().setId(selectByPrimaryKey(blog.getId()).getBlogInfo().getId());
        blogInfoService.updateByPrimaryKeySelective(blog.getBlogInfo());
        return mapper.updateByPrimaryKeySelective(blog);
    }

    @Transactional
    public List selectAll(){
        return mapper.selectAll();
    }

}
