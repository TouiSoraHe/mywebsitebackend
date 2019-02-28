package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

//    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    List<Comment> selectAll();

    List<Comment> selectByBlogIDWithRootNode(Integer blogID, String sort, String order, Integer offset, Integer count);

    int selectByBlogIDRootCount(Integer blogID);

    List<Comment> selectByParentIDs(List<Integer> parentIds);

//    int updateByPrimaryKey(Comment record);
}