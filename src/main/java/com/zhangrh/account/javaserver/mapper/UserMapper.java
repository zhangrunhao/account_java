package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
  @Select("SELECT * FROM users WHERE users_id = #{id}")
  User getById(@Param("id") long id);

  @Select("SELECT * FROM users WHERE email = #{email}")
  User getUserByEmail(@Param("email") String email);

  @Options(useGeneratedKeys = true, keyProperty = "usersId", keyColumn = "users_id")
  @Insert("INSERT INTO users (email,password,create_at) VALUES (#{user.email},#{user.password},#{user.createAt})")
  void insert(@Param("user") User user);

}
