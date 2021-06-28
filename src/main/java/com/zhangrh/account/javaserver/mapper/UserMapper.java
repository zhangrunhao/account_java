package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
  @Select("SELECT * FROM users WHERE users_id = #{id} AND delete_at IS NULL")
  User getById(@Param("id") long id);

  @Delete("DELETE FROM users WHERE users_id = #{id} AND delete_at IS NULL")
  int deleteUserFromDatabaseById(@Param("id") long id);

  @Select("SELECT * FROM users WHERE email = #{email} AND delete_at IS NULL")
  @Results({
    @Result(property = "userId", column = "users_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  User getUserByEmail(@Param("email") String email);

  @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "users_id")
  @Insert("INSERT INTO users (email,password,create_at) VALUES (#{user.email},#{user.password},#{user.createAt})")
  void insert(@Param("user") User user);

}
