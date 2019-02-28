package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Blog;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

//    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    List<Blog> selectAll();

    int updateByPrimaryKeySelective(Blog record);

//    int updateByPrimaryKey(Blog record);
}