package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface BlogInfoMapper {
    @Delete({
        "delete from blog_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into blog_info (title, time, ",
        "views, words, blog_id, ",
        "last_modified, deleted, ",
        "summary, img_url, ",
        "tags)",
        "values (#{title,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{views,jdbcType=INTEGER}, #{words,jdbcType=INTEGER}, #{blog_id,jdbcType=INTEGER}, ",
        "#{last_modified,jdbcType=TIMESTAMP}, #{deleted,jdbcType=BIT}, ",
        "#{summary,jdbcType=LONGVARCHAR}, #{img_url,jdbcType=LONGVARBINARY}, ",
        "#{tags,jdbcType=LONGVARBINARY})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(BlogInfo record);

    @Select({
        "select",
        "id, title, time, views, words, blog_id, last_modified, deleted, summary, img_url, ",
        "tags",
        "from blog_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="blog_id", property="blog_id", jdbcType=JdbcType.INTEGER),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="summary", property="summary", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="tags", property="tags", jdbcType=JdbcType.LONGVARBINARY)
    })
    BlogInfo selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, title, time, views, words, blog_id, last_modified, deleted, summary, img_url, ",
        "tags",
        "from blog_info"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="blog_id", property="blog_id", jdbcType=JdbcType.INTEGER),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="summary", property="summary", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="tags", property="tags", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<BlogInfo> selectAll();

    @Update({
        "update blog_info",
        "set title = #{title,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "views = #{views,jdbcType=INTEGER},",
          "words = #{words,jdbcType=INTEGER},",
          "blog_id = #{blog_id,jdbcType=INTEGER},",
          "last_modified = #{last_modified,jdbcType=TIMESTAMP},",
          "deleted = #{deleted,jdbcType=BIT},",
          "summary = #{summary,jdbcType=LONGVARCHAR},",
          "img_url = #{img_url,jdbcType=LONGVARBINARY},",
          "tags = #{tags,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BlogInfo record);
}