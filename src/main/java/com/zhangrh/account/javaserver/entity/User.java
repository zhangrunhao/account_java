package com.zhangrh.account.javaserver.entity;

public class User extends AbstractEntity {
  private long usersId;
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public long getUsersId() {
    return usersId;
  }

  public void setUsersId(long usersId) {
    this.usersId = usersId;
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
