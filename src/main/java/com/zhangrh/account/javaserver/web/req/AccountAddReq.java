package com.zhangrh.account.javaserver.web.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AccountAddReq {

  @NotBlank(message = "不可为空")
  private String name;

  @NotBlank(message = "不可为空")
  private String icon;

  @NotNull(message = "不可为空")
  private int cate;

}
