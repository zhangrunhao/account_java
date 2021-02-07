package com.zhangrh.account.javaserver.service;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
  @Autowired
  UserMapper userMapper;

  public User getUserById(long id) {
    User user = userMapper.getById(id);
    if (user == null) {
      throw new RuntimeException("User not found by id");
    }
    return user;
  }
}
