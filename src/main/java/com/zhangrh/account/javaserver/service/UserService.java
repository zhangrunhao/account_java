package com.zhangrh.account.javaserver.service;
public interface UserService {
  /**
   * 登录功能
   * @param email 用户邮箱
   * @param password 用户密码
   * @return token 登录token
   */
  String login(String email, String password);
}

