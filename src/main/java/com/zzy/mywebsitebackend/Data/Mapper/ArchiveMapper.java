package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.Archive;

import java.util.List;

import com.zzy.mywebsitebackend.Data.Provider.ArchiveProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ArchiveMapper {
    @Delete({
            "delete from archive",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into archive (blog_info_id, tag_id)",
            "values (#{blog_info_id,jdbcType=INTEGER}, #{tag_id,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Archive record);

    @Select({
            "select",
            "id, blog_info_id, tag_id",
            "from archive",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "blog_info_id", property = "blog_info_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "tag_id", property = "tag_id", jdbcType = JdbcType.INTEGER)
    })
    Archive selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, blog_info_id, tag_id",
            "from archive"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "blog_info_id", property = "blog_info_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "tag_id", property = "tag_id", jdbcType = JdbcType.INTEGER)
    })
    List<Archive> selectAll();

    @Update({
            "update archive",
            "set blog_info_id = #{blog_info_id,jdbcType=INTEGER},",
            "tag_id = #{tag_id,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Archive record);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Delete({
            "delete from archive",
            "where blog_info_id = #{id,jdbcType=INTEGER}"
    })
    int deleteByBlogInfoID(Integer id);

    @Delete({
            "delete from archive",
            "where tag_id = #{id,jdbcType=INTEGER}"
    })
    int deleteByTagID(Integer id);

    @DeleteProvider(type = ArchiveProvider.class, method = "deleteByTagIDAndBlogInfoIDs")
    int deleteByTagIDAndBlogInfoIDs(@Param("tagId") Integer tagId, @Param("blogInfoIds") List<Integer> blogInfoIds);

    @DeleteProvider(type = ArchiveProvider.class, method = "deleteByBlogInfoIDAndTagIDs")
    int deleteByBlogInfoIDAndTagIDs(@Param("blogInfoId") Integer blogInfoId, @Param("tagIds") List<Integer> tagIds);

    @Select({
            "select",
            "id, blog_info_id, tag_id",
            "from archive",
            "where blog_info_id = #{blog_info_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "blog_info_id", property = "blog_info_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "tag_id", property = "tag_id", jdbcType = JdbcType.INTEGER)
    })
    List<Archive> selectByBlogInfoID(@Param("blog_info_id") Integer blogInfoId);

    @Select({
            "select",
            "id, blog_info_id, tag_id",
            "from archive",
            "where tag_id = #{tagId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "blog_info_id", property = "blog_info_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "tag_id", property = "tag_id", jdbcType = JdbcType.INTEGER)
    })
    List<Archive> selectByTagID(@Param("tagId") Integer tagId);

    @SelectProvider(type = ArchiveProvider.class, method = "selectByTagIDs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "blog_info_id", property = "blog_info_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "tag_id", property = "tag_id", jdbcType = JdbcType.INTEGER)
    })
    List<Archive> selectByTagIDs(@Param("tagIds") List<Integer> tagIds);

    @InsertProvider(type = ArchiveProvider.class, method = "insterByList")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insterByList(@Param("archives") List<Archive> archives);

}