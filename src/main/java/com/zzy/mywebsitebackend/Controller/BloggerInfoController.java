package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.BloggerInfo;
import com.zzy.mywebsitebackend.Service.BloggerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blogger-info")
@Slf4j
public class BloggerInfoController {

    @Autowired
    private BloggerInfoService bloggerInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBloggerInfo() {
        return new ResponseEntity(bloggerInfoService.getBloggerInfo(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @RequiresPermissions(logical = Logical.AND, value = {"Edit"})
    public ResponseEntity updateBlog(@RequestBody @Validated BloggerInfo bloggerInfo){
        bloggerInfoService.updateBloggerInfo(bloggerInfo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
