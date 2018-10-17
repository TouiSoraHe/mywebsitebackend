package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.POJO.BlogJsonObj;
import com.zzy.mywebsitebackend.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/{blogID}",method = RequestMethod.GET)
    public ResponseEntity getBlog(@PathVariable("blogID")Integer blogID) {
        Blog blog = blogService.selectByPrimaryKey(blogID);
        if(blog == null){
            String msg = "没有找到ID为"+blogID+"的博客";
            log.error("getBlog:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new BlogJsonObj(blog),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBlogs() {
        List<Blog> blogs = blogService.selectAll();
        List<BlogJsonObj> ret = new ArrayList<>();
        for(Blog item:blogs){
            ret.add(new BlogJsonObj(item));
        }
        return new ResponseEntity(ret,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBlog(@RequestBody  @Validated BlogJsonObj blogJsonObj){
        blogJsonObj.setTime(new Date());
        blogJsonObj.setLastModified(new Date());
        blogJsonObj.setDeleted(false);
        blogJsonObj.setWords(blogJsonObj.getContent().length());
        Blog blog = blogJsonObj.toBlog();
        int isSuccess = blogService.insert(blog);
        if(isSuccess == 1)
            return  new ResponseEntity(new BlogJsonObj(blog),HttpStatus.CREATED);
        String msg = "addBlog:新增博客失败,"+ new BlogJsonObj(blog).toString();
        log.error(msg);
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.PUT)
    public ResponseEntity updateBlog(@PathVariable("blogID")Integer id,@RequestBody  @Validated BlogJsonObj blogJsonObj){
        BlogJsonObj oldBlogJsonObj = new BlogJsonObj(blogService.selectByPrimaryKey(id));
        if(oldBlogJsonObj == null){
            return new ResponseEntity("没有找到ID为"+id+"的博客",HttpStatus.NOT_FOUND);
        }
        blogJsonObj.setId(oldBlogJsonObj.getId());
        blogJsonObj.setTime(oldBlogJsonObj.getTime());
        blogJsonObj.setLastModified(new Date());
        blogJsonObj.setViews(oldBlogJsonObj.getViews());
        blogJsonObj.setWords(blogJsonObj.getContent().length());
        int isSuccess = blogService.updateByPrimaryKey(blogJsonObj.toBlog());
        if(isSuccess == 1)
            return new ResponseEntity(blogJsonObj,HttpStatus.OK);
        String msg = "updateBlog:更新博客失败,"+ blogJsonObj.toString();
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteBlog(@PathVariable("blogID")Integer blogID){
        int isSuccess = blogService.deleteByPrimaryKey(blogID);
        if(isSuccess == 1){
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }
        String msg = "deleteBlog:没有找到该博客,blogID:"+blogID;
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
    }

}
