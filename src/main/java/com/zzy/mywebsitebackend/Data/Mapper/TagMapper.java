package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Tag;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TagMapper {
    @Delete({
        "delete from tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tag (tag_name, tag_img, ",
        "blog_info_id)",
        "values (#{tag_name,jdbcType=VARCHAR}, #{tag_img,jdbcType=VARCHAR}, ",
        "#{blog_info_id,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Tag record);

    @Select({
        "select",
        "id, tag_name, tag_img, blog_info_id",
        "from tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_name", property="tag_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="tag_img", property="tag_img", jdbcType=JdbcType.VARCHAR),
        @Result(column="blog_info_id", property="blog_info_id", jdbcType=JdbcType.VARCHAR)
    })
    Tag selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, tag_name, tag_img, blog_info_id",
        "from tag"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag_name", property="tag_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="tag_img", property="tag_img", jdbcType=JdbcType.VARCHAR),
        @Result(column="blog_info_id", property="blog_info_id", jdbcType=JdbcType.VARCHAR)
    })
    List<Tag> selectAll();

    @Update({
        "update tag",
        "set tag_name = #{tag_name,jdbcType=VARCHAR},",
          "tag_img = #{tag_img,jdbcType=VARCHAR},",
          "blog_info_id = #{blog_info_id,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tag record);
}