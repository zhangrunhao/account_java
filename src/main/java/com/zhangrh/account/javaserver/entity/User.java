package com.zhangrh.account.javaserver.entity;

public class User extends AbstractEntity {
  private long userId;
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
