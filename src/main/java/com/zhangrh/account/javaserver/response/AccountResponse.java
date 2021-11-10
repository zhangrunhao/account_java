package com.zhangrh.account.javaserver.response;

import java.math.BigDecimal;

public class AccountResponse {
  private long accountId;
  private String icon;
  private String name;
  private String type;
  private String color;
  private BigDecimal count;
  public long getAccountId() {
    return accountId;
  }
  public BigDecimal getCount() {
    return count;
  }
  public void setCount(BigDecimal count) {
    this.count = count;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
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
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
}

