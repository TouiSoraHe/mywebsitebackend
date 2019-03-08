package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.BackendUser;

public interface BackendUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackendUser record);

//    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackendUser record);

    BackendUser selectByUserName(String username);

//    int updateByPrimaryKey(BackendUser record);
}