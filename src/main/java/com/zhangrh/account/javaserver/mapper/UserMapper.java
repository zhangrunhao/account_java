package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO user (email,password,create_at) VALUES (#{user.email}, #{user.password}, #{user.createAt})")
  int insert(@Param("user") User user);

  @Select("SELECT * FROM user WHERE id=#{id}")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  User queryId(@Param("id") long id);

  @Select("SELECT * FROM user WHERE email=#{email}")
  @Results({
    @Result(property = "id", column = "id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  User queryEmail(@Param("email") String email);
}
