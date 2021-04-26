package com.zhangrh.account.javaserver.service;

import java.util.Map;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
  @Autowired
  UserMapper userMapper;
  
  public User getUserById(long id) {
    return userMapper.getById(id);
  }

  public Map<String, Object> signin(String email, String password) {
    User user = userMapper.getUserByEmail(email);
    if (user != null && user.getPassword().equals(password)) {
      return Map.of("User", user);
    }
    return Map.of("error", "SIGNIN_FAILED");
  }
}
