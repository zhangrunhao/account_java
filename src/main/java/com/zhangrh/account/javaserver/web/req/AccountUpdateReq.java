package com.zhangrh.account.javaserver.web.req;

import lombok.Data;

@Data
public class AccountUpdateReq {
  private Long id;
  private String name;
  private String icon;
  private int cate;
}
