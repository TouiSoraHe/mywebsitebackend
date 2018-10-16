package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Data.Mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBlogs() {
        List<Blog> ret = blogMapper.selectAll();
        System.out.println(ret.size());
        return ResponseEntity.ok(ret);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBlog(@RequestBody  @Validated Blog blog){
        blog.setTime(new Date());
        blog.setLast_modified(new Date());
        blog.setDeleted(false);
        blogMapper.insert(blog);
        return  new ResponseEntity(blog,HttpStatus.CREATED);
    }

}
