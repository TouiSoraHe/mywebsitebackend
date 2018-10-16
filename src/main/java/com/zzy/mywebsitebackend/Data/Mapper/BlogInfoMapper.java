package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface BlogInfoMapper {
    @Delete({
        "delete from blog_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into blog_info (title, time, ",
        "views, words, img_url, ",
        "tags, blog_id, last_modified, ",
        "deleted, summary)",
        "values (#{title,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{views,jdbcType=INTEGER}, #{words,jdbcType=INTEGER}, #{img_url,jdbcType=VARCHAR}, ",
        "#{tags,jdbcType=VARCHAR}, #{blog_id,jdbcType=INTEGER}, #{last_modified,jdbcType=TIMESTAMP}, ",
        "#{deleted,jdbcType=BIT}, #{summary,jdbcType=LONGVARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(BlogInfo record);

    @Select({
        "select",
        "id, title, time, views, words, img_url, tags, blog_id, last_modified, deleted, ",
        "summary",
        "from blog_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.VARCHAR),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="blog_id", property="blog_id", jdbcType=JdbcType.INTEGER),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="summary", property="summary", jdbcType=JdbcType.LONGVARCHAR)
    })
    BlogInfo selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, title, time, views, words, img_url, tags, blog_id, last_modified, deleted, ",
        "summary",
        "from blog_info"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="views", property="views", jdbcType=JdbcType.INTEGER),
        @Result(column="words", property="words", jdbcType=JdbcType.INTEGER),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.VARCHAR),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="blog_id", property="blog_id", jdbcType=JdbcType.INTEGER),
        @Result(column="last_modified", property="last_modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="summary", property="summary", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogInfo> selectAll();

    @Update({
        "update blog_info",
        "set title = #{title,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "views = #{views,jdbcType=INTEGER},",
          "words = #{words,jdbcType=INTEGER},",
          "img_url = #{img_url,jdbcType=VARCHAR},",
          "tags = #{tags,jdbcType=VARCHAR},",
          "blog_id = #{blog_id,jdbcType=INTEGER},",
          "last_modified = #{last_modified,jdbcType=TIMESTAMP},",
          "deleted = #{deleted,jdbcType=BIT},",
          "summary = #{summary,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BlogInfo record);
}