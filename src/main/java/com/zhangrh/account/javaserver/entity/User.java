package com.zhangrh.account.javaserver.entity;

public class User extends AbstractEntity {
  private Long usersId;
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public Long getUsersId() {
    return usersId;
  }

  public void setUsersId(Long usersId) {
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
