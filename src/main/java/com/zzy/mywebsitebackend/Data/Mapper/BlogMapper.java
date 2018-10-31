package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import java.util.List;

import com.zzy.mywebsitebackend.Data.Provider.BlogProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface BlogMapper {
    @Delete({
        "delete from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into blog (content)",
        "values (#{content,jdbcType=LONGVARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Blog record);

    @Select({
        "select",
        "id, content",
        "from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Blog selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, content",
        "from blog"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Blog> selectAll();

    @Update({
        "update blog",
        "set content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Blog record);

    //////////////////////////////////////////////////////////////////////////////////////

}