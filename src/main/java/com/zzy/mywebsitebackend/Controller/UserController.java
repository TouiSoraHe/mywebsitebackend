package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getUser(@PathVariable("userId")String userId) {
        User user = userService.selectByPrimaryKey(userId);
        if(user == null){
            String msg = "没有找到ID为"+userId+"的用户";
            log.error("getUser:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getUsers() {
        List<User> userJsonObjs = userService.selectAll();
        return new ResponseEntity(userJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Add"})
    public ResponseEntity addUser(@RequestBody  @Validated User user){
        userService.insert(user);
        return  new ResponseEntity(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Edit"})
    public ResponseEntity updateUser(@PathVariable("userId")String userId, @RequestBody @Validated User user){
        user.setId(userId);
        userService.updateByPrimaryKeySelective(user);
        return new ResponseEntity(userService.selectByPrimaryKey(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Delete"})
    public  ResponseEntity deleteUser(@PathVariable("userId")String userId){
        userService.deleteByPrimaryKey(userId);
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }
}
