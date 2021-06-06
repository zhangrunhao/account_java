package com.zhangrh.account.javaserver.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AccountAddParam {
  @NotEmpty(message = "账户名称不可为空")
  private String name;

  @NotEmpty(message = "账户图标不可为空")
  private String icon;

  @NotEmpty(message = "账户颜色不可为空")
  private String color;

  @NotEmpty(message = "账户类型不可为空")
  private String type;

  public String getName() {
    return name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public void setName(String name) {
    this.name = name;
  }

}
