package com.zhangrh.account.javaserver.service.impl;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.UserMapper;
import com.zhangrh.account.javaserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserMapper userMapper;

  @Override
  public User getUserById(long id) {
    return userMapper.getById(id);
  }

  @Override
  public User login(String email, String password) {
    User user = null;
    user = userMapper.getUserByEmail(email);
    if (user == null) {
      Asserts.fail("用户不存在");
    } else if (!user.getPassword().equals(password)) {
      Asserts.fail("密码错误");
    }
    return user;
  }
  
}
