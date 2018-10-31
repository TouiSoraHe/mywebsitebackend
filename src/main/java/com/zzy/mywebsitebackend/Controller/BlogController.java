package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.JsonObj.BlogJsonObj;
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

import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/{blogID}",method = RequestMethod.GET)
    public ResponseEntity getBlog(@PathVariable("blogID")Integer blogID) {
        BlogJsonObj blogJsonObj = blogService.selectByPrimaryKey(blogID);
        if(blogJsonObj == null){
            String msg = "没有找到ID为"+blogID+"的博客";
            log.error("getBlog:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(blogJsonObj,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBlogs() {
        List<BlogJsonObj> blogJsonObjs = blogService.selectAll();
        return new ResponseEntity(blogJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBlog(@RequestBody  @Validated BlogJsonObj blogJsonObj){
        int isSuccess = blogService.insert(blogJsonObj);
        if(isSuccess == 1)
            return  new ResponseEntity(blogJsonObj,HttpStatus.CREATED);
        String msg = "addBlog:新增博客失败,"+ blogJsonObj.toString();
        log.error(msg);
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{blogID}",method = RequestMethod.PUT)
    public ResponseEntity updateBlog(@PathVariable("blogID")Integer id,@RequestBody  @Validated BlogJsonObj blogJsonObj){
        BlogJsonObj oldBlogJsonObj = blogService.selectByPrimaryKey(id);
        if(oldBlogJsonObj == null){
            return new ResponseEntity("没有找到ID为"+id+"的博客",HttpStatus.NOT_FOUND);
        }
        blogJsonObj.setId(oldBlogJsonObj.getId());
        int isSuccess = blogService.updateByPrimaryKey(blogJsonObj);
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
