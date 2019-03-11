package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Component.ViewsCount;
import com.zzy.mywebsitebackend.Data.Entity.Blog;
import com.zzy.mywebsitebackend.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private ViewsCount viewsCount;

    @RequestMapping(value = "/{blogID}",method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlog(@PathVariable("blogID")Integer blogID, HttpServletRequest request) {
        Blog blog = blogService.selectByPrimaryKey(blogID);
        if (blog == null || (blog.getBlogInfo().getDeleted() && !SecurityUtils.getSubject().hasRole("admin"))) {
            String msg = "没有找到ID为"+blogID+"的博客";
            log.error("getBlog:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        viewsCount.AddCount(blog.getBlogInfo().getId(),request);
        blog.getBlogInfo().setViews(blog.getBlogInfo().getViews() + viewsCount.GetCount(blog.getBlogInfo().getId()));
        return new ResponseEntity(blog,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlogs() {
        List<Blog> blogs = blogService.selectAll();
        Subject subject = SecurityUtils.getSubject();
        for (Iterator<Blog> iter = blogs.listIterator(); iter.hasNext(); ) {
            Blog blog = iter.next();
            if (blog.getBlogInfo().getDeleted() && !SecurityUtils.getSubject().hasRole("admin")){
                iter.remove();
            }
        }
        return new ResponseEntity(blogs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Add"})
    public ResponseEntity addBlog(@RequestBody  @Validated Blog blog){
        blogService.insert(blog);
        return  new ResponseEntity(blog,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.PUT)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Edit"})
    public ResponseEntity updateBlog(@PathVariable("blogID")Integer id,@RequestBody  @Validated Blog blog){
        blog.setId(id);
        blogService.updateByPrimaryKeySelective(blog);
        return new ResponseEntity(blogService.selectByPrimaryKey(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.DELETE)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Delete"})
    public  ResponseEntity deleteBlog(@PathVariable("blogID")Integer blogID){
        blogService.deleteByPrimaryKey(blogID);
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }
}
