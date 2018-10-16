package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BlogMapper {
    @Delete({
        "delete from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into blog (title, time, ",
        "views, words, tags, ",
        "last_modified, deleted, ",
        "content, img_url)",
        "values (#{title,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{views,jdbcType=INTEGER}, #{words,jdbcType=INTEGER}, #{tags,jdbcType=VARCHAR}, ",
        "#{last_modified,jdbcType=TIMESTAMP}, #{deleted,jdbcType=BIT}, ",
        "#{content,jdbcType=LONGVARCHAR}, #{img_url,jdbcType=LONGVARBINARY})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Blog record);

    @Select({
        "select",
        "id, title, time, views, words, tags, last_modified, deleted, content, img_url",
        "from blog",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY)
    })
    Blog selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, title, time, views, words, tags, last_modified, deleted, content, img_url",
        "from blog"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<Blog> selectAll();

    @Update({
        "update blog",
        "set title = #{title,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "views = #{views,jdbcType=INTEGER},",
          "words = #{words,jdbcType=INTEGER},",
          "tags = #{tags,jdbcType=VARCHAR},",
          "last_modified = #{last_modified,jdbcType=TIMESTAMP},",
          "deleted = #{deleted,jdbcType=BIT},",
          "content = #{content,jdbcType=LONGVARCHAR},",
          "img_url = #{img_url,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Blog record);
}