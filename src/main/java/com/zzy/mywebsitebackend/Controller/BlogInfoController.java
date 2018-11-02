package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.JsonObj.BlogInfoJsonObj;
import com.zzy.mywebsitebackend.Service.BlogInfoService;
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
@RequestMapping("/blog-info")
@Slf4j
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;

    @RequestMapping(value = "/{blogInfoId}",method = RequestMethod.GET)
    public ResponseEntity getBlogInfo(@PathVariable("blogInfoId")Integer blogInfoId) {
        BlogInfoJsonObj blogInfoJsonObj = blogInfoService.selectByPrimaryKey(blogInfoId);
        if(blogInfoJsonObj == null){
            String msg = "没有找到ID为"+blogInfoId+"的博客信息";
            log.error("getBlogInfo:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(blogInfoJsonObj,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBlogInfos() {
        List<BlogInfoJsonObj> blogInfoJsonObjs = blogInfoService.selectAll();
        return new ResponseEntity(blogInfoJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogInfoId}",method = RequestMethod.PUT)
    public ResponseEntity updateBlogInfo(@PathVariable("blogInfoId")Integer id, @RequestBody @Validated BlogInfoJsonObj blogInfoJsonObj){
        blogInfoJsonObj.setId(id);
        int isSuccess = blogInfoService.updateByPrimaryKey(blogInfoJsonObj);
        if(isSuccess == 1)
            return new ResponseEntity(blogInfoJsonObj,HttpStatus.OK);
        String msg = "updateBlogInfo:更新博客信息失败,"+ blogInfoJsonObj.toString();
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }
}
