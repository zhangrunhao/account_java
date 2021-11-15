package com.zhangrh.account.javaserver.entity;

public class User extends AbstractEntity {
  private long id;
  private String email;
  private String password;
  public long getId() {
    return id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setId(long id) {
    this.id = id;
  }
}
