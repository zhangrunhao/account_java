package com.zhangrh.account.javaserver.web.req;

import lombok.Data;

@Data
public class TradeTransferReq {
  private long inAccountId;
  private long outAccountId;
  private String money;
  private long spendDate;
}
