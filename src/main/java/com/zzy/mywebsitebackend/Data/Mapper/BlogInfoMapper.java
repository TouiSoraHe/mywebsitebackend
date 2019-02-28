package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;

import java.util.List;

public interface BlogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogInfo record);

//    int insertSelective(BlogInfo record);

    BlogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogInfo record);

//    int updateByPrimaryKey(BlogInfo record);

    List<BlogInfo> selectAll();

    List<BlogInfo> selectByLimit(String sort, String order, Integer offset, Integer count);

    List<BlogInfo> selectByIds(String sort, String order, Integer[] ids);

    int selectCount();
}