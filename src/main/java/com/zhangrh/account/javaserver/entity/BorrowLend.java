package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowLend extends AbstractEntity {
  private long id;
  private long borrowLendTradeId;
  private long repaymentReceiveTradeId;
}
