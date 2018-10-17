package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TagMapper {
    @Delete({
        "delete from tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tag (tag_name, img_url, ",
        "blog_info_id)",
        "values (#{tag_name,jdbcType=VARCHAR}, #{img_url,jdbcType=LONGVARBINARY}, ",
        "#{blog_info_id,jdbcType=LONGVARBINARY})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Tag record);

    @Select({
        "select",
        "id, tag_name, img_url, blog_info_id",
        "from tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_name", property="tag_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="blog_info_id", property="blog_info_id", jdbcType=JdbcType.LONGVARBINARY)
    })
    Tag selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, tag_name, img_url, blog_info_id",
        "from tag"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_name", property="tag_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="img_url", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="blog_info_id", property="blog_info_id", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<Tag> selectAll();

    @Update({
        "update tag",
        "set tag_name = #{tag_name,jdbcType=VARCHAR},",
          "img_url = #{img_url,jdbcType=LONGVARBINARY},",
          "blog_info_id = #{blog_info_id,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tag record);
}