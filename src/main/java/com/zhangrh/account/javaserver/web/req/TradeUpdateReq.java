package com.zhangrh.account.javaserver.web.req;

import lombok.Data;

@Data
public class TradeUpdateReq {
  private long id;
  private long accountId;
  private long tradeCateId;
  private String money;
  private String remark;
  private long spendDate;
  private int operate;
}
