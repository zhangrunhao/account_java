package com.zhangrh.account.javaserver.service;

import com.zhangrh.account.javaserver.entity.User;

public interface UserService {

  /**
   * 根据邮箱获取用户id
   * @param email 邮箱
   * @return user 用户
   */
  User getUserFromEmail(String email);

  /**
   * 根据id删除用户
   * @param id 用户id
   */
  void deleteUserById(long id);

  /**
   * 登录功能
   * @param email 用户邮箱
   * @param password 用户密码
   * @return token 登录token
   */
  String login(String email, String password);

  /**
   * 注册功能
   * @param email 邮箱
   * @param password 密码
   * @return 用户信息
   */
  void register(String email, String password);
}

