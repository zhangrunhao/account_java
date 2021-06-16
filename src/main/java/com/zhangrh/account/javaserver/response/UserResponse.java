package com.zhangrh.account.javaserver.response;

public class UserResponse {
  private long userId;
  private String email;

  public String getEmail() {
    return email;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

