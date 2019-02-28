package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

//    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

//    int updateByPrimaryKey(Tag record);

    /**
     * 该方法会忽略重复的tag,但是会导致tag的id错乱
     * @param tags
     * @return
     */
    int insertAll(List<Tag> tags);

    List<Tag> selectByTagName(List<String> tagName);

    List<Tag> selectAll();
}