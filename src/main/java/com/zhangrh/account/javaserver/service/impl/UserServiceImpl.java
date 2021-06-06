package com.zhangrh.account.javaserver.service.impl;

import java.util.Date;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.UserMapper;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserMapper userMapper;

  @Override
  public String login(String email, String password) {
    String token = null;
    try {
      User user = userMapper.getUserByEmail(email);
      if (user == null) {
        Asserts.fail("用户名不存在");
      }
      if (!user.getPassword().equals(password)) {
        Asserts.fail("密码不正确");
      }
      token = JwtTokenUtil.generateToken(user.getEmail());
    } catch (Exception e) {
      LOG.warn("登录异常: " + e.getMessage());
    }
    return token;
  }

  @Override
  public User register(String email, String password) {
    User user = new User();
    if (userMapper.getUserByEmail(email) != null) {
      Asserts.fail("邮箱已被注册");
    }
    try {
      user.setEmail(email);
      user.setPassword(password);
      user.setCreateAt(new Date().getTime());
      userMapper.insert(user);
      return user;
    } catch (Exception e) {
      Asserts.fail("用户插入失败");
    }
    return null;
  }

  @Override
  public User getUserFromEmail(String email) {
    User user = userMapper.getUserByEmail(email);
    return user;
  }
}
