package com.zhangrh.account.javaserver.entity;

public class Account extends AbstractEntity{
  private long id;
  private String name;
  private String type;
  private String cate;
  private String icon;
  public long getId() {
    return id;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public String getCate() {
    return cate;
  }
  public void setCate(String cate) {
    this.cate = cate;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
