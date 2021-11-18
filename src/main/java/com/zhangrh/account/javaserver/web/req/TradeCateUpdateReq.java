package com.zhangrh.account.javaserver.web.req;

import lombok.Data;

@Data
public class TradeCateUpdateReq {
  private long id;
  private String name;
  private String icon;
  private int operate; // 只能传入 1 支出 / 2 收入
}
