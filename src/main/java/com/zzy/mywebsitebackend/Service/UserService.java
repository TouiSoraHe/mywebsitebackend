
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.User;
import com.zzy.mywebsitebackend.Data.JsonObj.UserJsonObj;
import com.zzy.mywebsitebackend.Data.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 添加用户
     * @param userJsonObj
     * @return
     */
    public int insert(UserJsonObj userJsonObj){
        User user = userJsonObj.toUser();
        int ret = mapper.insert(user);
        userJsonObj.setWithUser(user);
        return ret;
    }

    /**
     * 更新用户
     * @param userJsonObj
     * @return
     */
    public int updateByPrimaryKey(UserJsonObj userJsonObj){
        User user = userJsonObj.toUser();
        int ret = mapper.updateByPrimaryKey(user);
        userJsonObj.setWithUser(user);
        return ret;
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public UserJsonObj selectByPrimaryKey(String id){
        User user = mapper.selectByPrimaryKey(id);
        if(user == null) return null;
        return new UserJsonObj(user);
    }

    /**
     * 根据id列表批量获取用户
     * @param ids
     * @return
     */
    public List<UserJsonObj> selectByPrimaryKeyList(Set<String> ids){
        List<User> users = mapper.selectByPrimaryKeyList(ids);
        if(users.size() == 0 ) return new ArrayList<>();
        List<UserJsonObj> userJsonObjs = new ArrayList<>();
        for (User user:users){
            userJsonObjs.add(new UserJsonObj(user));
        }
        return  userJsonObjs;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id){
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取所有用户
     * @return
     */
    public List selectAll(){
        return mapper.selectAll();
    }

}
