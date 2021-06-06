package com.zhangrh.account.javaserver.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserParam {

  @NotEmpty(message = "用户邮箱不可为空")
  @Email(message = "邮箱格式不正确")
  private String email;

  @NotEmpty(message = "用户密码不可为空")
  private String password;

  public String getEmail() {
    return email;
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
