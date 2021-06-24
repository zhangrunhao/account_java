package com.zhangrh.account.javaserver.request;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AccountUpdateRequest {
  @Min(value = 1, message = "accountId至少为1")
  private Long accountId;
  @NotEmpty(message = "账户名称不可为空")
  private String name;
  @NotEmpty(message = "账户图标不可为空")
  private String icon;
  @NotEmpty(message = "账户颜色不可为空")
  private String color;
  @NotEmpty(message = "账户类型不可为空")
  private String type;
  public long getAccountId() {
    return accountId;
  }
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
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
