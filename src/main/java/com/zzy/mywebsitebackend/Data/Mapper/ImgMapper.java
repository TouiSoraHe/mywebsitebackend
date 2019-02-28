package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Img;

import java.util.List;

public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

//    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

//    int updateByPrimaryKey(Img record);

    int insertAll(List<Img> imgs);
}