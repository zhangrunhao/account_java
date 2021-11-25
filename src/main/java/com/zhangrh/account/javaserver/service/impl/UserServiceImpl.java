package com.zhangrh.account.javaserver.service.impl;
import java.time.LocalDateTime;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.UserMapper;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.JwtTokenUtil;
import com.zhangrh.account.javaserver.utils.Md5Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  UserMapper userMapper;

  @Autowired
  AccountService accountService;

  @Override
  public String login(String email, String password) {
    String token = null;
    try {
      User user = userMapper.queryEmail(email);
      if (user == null) {
        Asserts.fail("用户名不存在");
      }
      if (!user.getPassword().equals(Md5Util.getMd5(password))) {
        Asserts.fail("密码不正确");
      }
      token = JwtTokenUtil.generateToken(user.getEmail());
    } catch (Exception e) {
      LOGGER.warn("登录异常: " + e.getMessage());
      Asserts.fail(e.getMessage());
    }
    return token;
  }

  @Override
  @Transactional
  public void register(String email, String password) {
    User user = new User();
    if (userMapper.queryEmail(email) != null) {
      Asserts.fail("邮箱已被注册");
    }
    try {
      user.setEmail(email);
      user.setPassword(Md5Util.getMd5(password));
      user.setCreateAt(LocalDateTime.now());
      userMapper.insert(user);
      // TODO: 插入成功后, 导入默认trade_cate
      accountService.addDefault(new UserBo(user));
    } catch (Exception e) {
      Asserts.fail("用户插入失败");
    }
  }

  @Override
  public UserBo getUserFromEmail(String email) {
    User user = null;
    try {
      user = userMapper.queryEmail(email);
    } catch (Exception e) {
      Asserts.fail("邮箱查询用户失败");
    }
    return user == null ? null : new UserBo(user);
  }
}
