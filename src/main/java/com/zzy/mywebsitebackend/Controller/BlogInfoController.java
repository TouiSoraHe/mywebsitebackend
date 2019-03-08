package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import com.zzy.mywebsitebackend.Service.BlogInfoService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/blog-info")
@Slf4j
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;

    @RequestMapping(value = "/{blogInfoId}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlogInfo(@PathVariable("blogInfoId") Integer blogInfoId) {
        BlogInfo blogInfo = blogInfoService.selectByPrimaryKey(blogInfoId);
        Subject subject = SecurityUtils.getSubject();
        if (blogInfo == null || (blogInfo.getDeleted() && !subject.hasRole("admin"))) {
            String msg = "没有找到ID为" + blogInfoId + "的博客信息";
            log.error("getBlogInfo:" + msg);
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(blogInfo, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getBlogInfos(String _sort, String _order, Integer _limit, Integer _page, Integer[] id, HttpServletResponse response) {
        List<BlogInfo> blogInfos = new ArrayList<>();
        if (_limit != null && _page != null) {
            Integer offset = (_page - 1) * _limit;
            blogInfos = blogInfoService.selectByLimit(_sort,_order,offset,_limit);
            int xTotalCount = blogInfoService.selectCount();
            response.setIntHeader("x-total-count",xTotalCount);
        }else if(id!=null && id.length>0){
            blogInfos = blogInfoService.selectByIds(_sort,_order,id);
        }else {
            blogInfos = blogInfoService.selectAll();
        }
        Subject subject = SecurityUtils.getSubject();
        for (Iterator<BlogInfo> iter = blogInfos.listIterator(); iter.hasNext(); ) {
            BlogInfo blogInfo = iter.next();
            if (blogInfo.getDeleted() && !subject.hasRole("admin")){
                iter.remove();
            }
        }
        return new ResponseEntity(blogInfos, HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogInfoId}", method = RequestMethod.PUT)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Edit"})
    public ResponseEntity updateBlogInfo(@PathVariable("blogInfoId") Integer id, @RequestBody @Validated BlogInfo blogInfo) {
        blogInfo.setId(id);
        blogInfoService.updateByPrimaryKeySelective(blogInfo);
        return new ResponseEntity(blogInfoService.selectByPrimaryKey(id), HttpStatus.OK);
    }
}
