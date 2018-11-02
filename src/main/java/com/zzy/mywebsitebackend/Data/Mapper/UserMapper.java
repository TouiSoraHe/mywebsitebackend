package com.zzy.mywebsitebackend.Data.Mapper;

import com.zzy.mywebsitebackend.Data.Entity.User;
import java.util.List;

import com.zzy.mywebsitebackend.Data.Provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into user (id, user_name, ",
        "email, avatar)",
        "values (#{id,jdbcType=VARCHAR}, #{user_name,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "id, user_name, email, avatar",
        "from user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String id);

    @Select({
        "select",
        "id, user_name, email, avatar",
        "from user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set user_name = #{user_name,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);

    ////////////////////////////////////////////////////////////////////////////////////

    @SelectProvider(type = UserProvider.class, method = "selectByPrimaryKeyList")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectByPrimaryKeyList(@Param("ids") List<String> ids);
}