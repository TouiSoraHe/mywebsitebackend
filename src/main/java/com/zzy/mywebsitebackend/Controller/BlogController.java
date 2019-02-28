package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/{blogID}",method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlog(@PathVariable("blogID")Integer blogID) {
        Blog blog = blogService.selectByPrimaryKey(blogID);
        if(blog == null){
            String msg = "没有找到ID为"+blogID+"的博客";
            log.error("getBlog:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(blog,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlogs() {
        List<Blog> blogs = blogService.selectAll();
        return new ResponseEntity(blogs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity addBlog(@RequestBody  @Validated Blog blog){
        blogService.insert(blog);
        return  new ResponseEntity(blog,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity updateBlog(@PathVariable("blogID")Integer id,@RequestBody  @Validated Blog blog){
        blog.setId(id);
        blogService.updateByPrimaryKeySelective(blog);
        return new ResponseEntity(blogService.selectByPrimaryKey(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.DELETE)
    @Transactional
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
