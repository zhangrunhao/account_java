package com.zhangrh.account.javaserver.enums;

import lombok.Getter;

public enum TradeOperation implements BaseCodeEnum{

  Income(1, "add"), // 收入
  Expend(2, "subtract"), // 支出
  Transfer_In(3, "add"), // 转入
  Transfer_Out(4, "subtract"), // 转出
  Borrow(5, "add"), // 借入
  Lend(6, "subtract"), // 借出
  Receive(7, "add"), // 收款
  Repayment(8, "subtract"), // 还款
  Init(9, "add"), // 初始化
  Flat(10, "add"); // 初始化

  private final int code;
  @Getter
  private final String sign;


  TradeOperation(int code, String sign) {
    this.code = code;
    this.sign = sign;
  }

  @Override
  public int getCode() {
    return this.code;
  }

  public static TradeOperation getByCode(int code) {
    for (TradeOperation operation: TradeOperation.values()) {
      if (code == operation.code) {
        return operation;
      }
    }
    return null;
  }
}
