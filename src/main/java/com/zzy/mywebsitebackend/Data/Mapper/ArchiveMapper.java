package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Archive;

import java.util.ArrayList;
import java.util.List;

public interface ArchiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Archive record);

//    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archive record);

//    int updateByPrimaryKey(Archive record);

    int insertAll(List<Archive> archives);

    List<Archive> selectByBlogInfoId(Integer id);

    int deleteAll(ArrayList<Archive> archives);

    List<Archive> selectByTagId(Integer id);
}