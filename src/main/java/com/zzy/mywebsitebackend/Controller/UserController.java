package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.JsonObj.TagJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.UserJsonObj;
import com.zzy.mywebsitebackend.Service.TagService;
import com.zzy.mywebsitebackend.Service.UserService;
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
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("userId")String userId) {
        UserJsonObj userJsonObj = userService.selectByPrimaryKey(userId);
        if(userJsonObj == null){
            String msg = "没有找到ID为"+userId+"的用户";
            log.error("getUser:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userJsonObj,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        List<UserJsonObj> userJsonObjs = userService.selectAll();
        return new ResponseEntity(userJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody  @Validated UserJsonObj userJsonObj){
        int isSuccess = userService.insert(userJsonObj);
        if(isSuccess == 1)
            return  new ResponseEntity(userJsonObj, HttpStatus.CREATED);
        String msg = "addUser:新增用户失败,"+ userJsonObj.toString();
        log.error(msg);
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("userId")String userId, @RequestBody @Validated UserJsonObj userJsonObj){
        userJsonObj.setId(userId);
        int isSuccess = userService.updateByPrimaryKey(userJsonObj);
        if(isSuccess == 1)
            return new ResponseEntity(userJsonObj, HttpStatus.OK);
        String msg = "updateUser:更新用户失败,"+ userJsonObj.toString();
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteUser(@PathVariable("userId")String userId){
        int isSuccess = userService.deleteByPrimaryKey(userId);
        if(isSuccess == 1){
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }
        String msg = "deleteUser:删除用户失败,userId:"+userId;
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
    }
}
