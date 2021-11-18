package com.zhangrh.account.javaserver.web.req;

import lombok.Data;

@Data
public class TradeCateAddReq {
  private String name;
  private String icon;
  private int operate; // 这里只能是,1 收入 / 2 支出
}
