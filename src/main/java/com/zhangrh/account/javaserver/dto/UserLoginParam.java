package com.zhangrh.account.javaserver.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserLoginParam {

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  @Size(min = 6, max = 12, message = "密码长度在6-12")
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
