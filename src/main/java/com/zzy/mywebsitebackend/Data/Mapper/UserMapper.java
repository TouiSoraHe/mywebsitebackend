package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

//    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    List<User> selectAll();

//    int updateByPrimaryKey(User record);
}