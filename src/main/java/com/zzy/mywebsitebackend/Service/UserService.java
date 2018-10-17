
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int insert(User var0){
        return mapper.insert(var0);
    }

    public int updateByPrimaryKey(User var0){
        return mapper.updateByPrimaryKey(var0);
    }

    public User selectByPrimaryKey(String var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public int deleteByPrimaryKey(String var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
