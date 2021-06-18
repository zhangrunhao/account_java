package com.zhangrh.account.javaserver.utils;

import com.zhangrh.account.javaserver.entity.User;

public class UserInfoUtil {
  private static ThreadLocal<User> userLocal = new ThreadLocal<>();

  public static User getUser() {
    return userLocal.get();
  }

  public static void setUser(User user) {
    userLocal.set(user);
  }

  public static void setDefaultTestUser(long userId) {
    User user = new User();
    user.setUserId(userId);
    userLocal.set(user);
  }
}
