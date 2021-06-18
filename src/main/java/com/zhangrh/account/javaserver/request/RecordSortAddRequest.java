package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.NotEmpty;

public class RecordSortAddRequest {
  @NotEmpty(message = "名称不可为空")
  private String name;
  @NotEmpty(message = "图标不可为空")
  private String icon;
  @NotEmpty(message = "类型不可为空")
  private String type;
  public String getType() {
    return type;
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
  public void setType(String type) {
    this.type = type;
  }
}
