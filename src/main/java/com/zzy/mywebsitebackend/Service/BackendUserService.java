
package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.BackendUser;
import com.zzy.mywebsitebackend.Data.Mapper.BackendUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendUserService {

    private static BackendUserService instance;

    public static BackendUserService getInstance() {
        return instance;
    }

    @Autowired
    private void setInstance(BackendUserService instance) {
        BackendUserService.instance = instance;
    }

    @Autowired
    private BackendUserMapper mapper;

    public int insert(BackendUser var0){
        return mapper.insert(var0);
    }

    public int deleteByPrimaryKey(Integer var0){
        return mapper.deleteByPrimaryKey(var0);
    }

    public int updateByPrimaryKeySelective(BackendUser var0){
        return mapper.updateByPrimaryKeySelective(var0);
    }

    public BackendUser selectByPrimaryKey(Integer var0){
        return mapper.selectByPrimaryKey(var0);
    }

    public BackendUser selectByUserName(String username){
        return mapper.selectByUserName(username);
    }

//    public int updateByPrimaryKey(BackendUser var0){
//        return mapper.updateByPrimaryKey(var0);
//    }
//
//    public int insertSelective(BackendUser var0){
//        return mapper.insertSelective(var0);
//    }

}
