package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.AOP.Entity.ExceptionInfo;
import com.zzy.mywebsitebackend.Service.ExceptionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/exception-info")
@Slf4j
public class ExceptionInfoController {

    @Autowired
    private ExceptionInfoService exceptionInfoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity getExceptionInfo(@PathVariable("id")Long id) {
        ExceptionInfo exceptionInfo = exceptionInfoService.getExceotionInfo(id);
        if (exceptionInfo == null) {
            String msg = "没有找到ID为"+id+"的异常信息";
            log.error("getExceptionInfo:"+msg);
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(exceptionInfo,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getExceptionInfos() {
        return new ResponseEntity(exceptionInfoService.getAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @RequiresPermissions(logical = Logical.AND, value = {"Delete"})
    public  ResponseEntity deleteBlog(@PathVariable("id")Long id){
        if(exceptionInfoService.removeExceptionInfo(id)){
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }else {
            return new ResponseEntity("没有找到ID为"+id+"的异常信息",HttpStatus.NOT_FOUND);
        }
    }
}
