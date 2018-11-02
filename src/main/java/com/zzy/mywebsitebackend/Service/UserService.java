
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.JsonObj.UserJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int insert(UserJsonObj userJsonObj){
        User user = userJsonObj.toUser();
        int ret = mapper.insert(user);
        userJsonObj.setWithUser(user);
        return ret;
    }

    public int updateByPrimaryKey(UserJsonObj userJsonObj){
        User user = userJsonObj.toUser();
        int ret = mapper.updateByPrimaryKey(user);
        userJsonObj.setWithUser(user);
        return ret;
    }

    public UserJsonObj selectByPrimaryKey(String var0){
        User user = mapper.selectByPrimaryKey(var0);
        if(user == null) return null;
        return new UserJsonObj(user);
    }

    public List<UserJsonObj> selectByPrimaryKeyList(List<String> ids){
        List<User> users = mapper.selectByPrimaryKeyList(ids);
        if(users.size() == 0 ) return new ArrayList<>();
        List<UserJsonObj> userJsonObjs = new ArrayList<>();
        for (User user:users){
            userJsonObjs.add(new UserJsonObj(user));
        }
        return  userJsonObjs;
    }

    public int deleteByPrimaryKey(String var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public List selectAll(){
        return mapper.selectAll();
    }

}
