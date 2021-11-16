package com.zhangrh.account.javaserver.entity;

import com.zhangrh.account.javaserver.enums.AccountType;

public class Account extends AbstractEntity{
  private long id;
  private String name;
  private AccountType type;
  private String cate;
  private String icon;
  public long getId() {
    return id;
  }
  public String getCate() {
    return cate;
  }
  public void setCate(String cate) {
    this.cate = cate;
  }
  public AccountType getType() {
    return type;
  }
  public void setType(AccountType type) {
    this.type = type;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setId(long id) {
    this.id = id;
  }
}
