package com.zhangrh.account.javaserver.utils;

import com.zhangrh.account.javaserver.service.Bo.UserBo;

public class UserInfoUtil {
  private static ThreadLocal<UserBo> userLocal = new ThreadLocal<>();

  public static UserBo getUser() {
    return userLocal.get();
  }

  public static void setUser(UserBo user) {
    userLocal.set(user);
  }
}
