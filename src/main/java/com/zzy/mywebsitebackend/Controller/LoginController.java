package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.BackendUser;
import com.zzy.mywebsitebackend.Service.BackendUserService;
import com.zzy.mywebsitebackend.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    BackendUserService backendUserService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody BackendUser user
                                , HttpServletResponse response) {
        String username = user.getUsername();
        String password = user.getPassword();
        BackendUser userBean = backendUserService.selectByUserName(username);
        if (userBean!=null && userBean.getPassword().equals(password)) {
            String token = JwtUtil.sign(username, password);
            response.setHeader("Authorization", token);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            return new ResponseEntity("登录成功", HttpStatus.OK);
        } else {
            return new ResponseEntity("登陆失败,用户名或密码错误",HttpStatus.UNAUTHORIZED);
        }
    }

}
