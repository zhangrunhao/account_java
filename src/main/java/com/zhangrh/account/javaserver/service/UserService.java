package com.zhangrh.account.javaserver.service;

import com.zhangrh.account.javaserver.entity.User;

public interface UserService {
  /**
   * 根据用户id, 获取用户
   * @param id 用户id
   * @return 用户
   */
  User getUserById(long id);

  /**
   * 登录功能
   * @param email 用户邮箱
   * @param password 用户密码
   * @return
   */
  User login(String email, String password);
}

