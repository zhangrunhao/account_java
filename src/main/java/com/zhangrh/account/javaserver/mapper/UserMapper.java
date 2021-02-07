package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
  @Select("SELECT * FROM users WHERE users_id = #{users_id}")
  User getById(@Param("users_id") long users_id);
}
