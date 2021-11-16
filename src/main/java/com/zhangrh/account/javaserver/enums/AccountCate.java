package com.zhangrh.account.javaserver.enums;

public enum AccountCate implements BaseCodeEnum{

  Property(1), // 资产
  Debt(2); // 负债

  private final int code;

  AccountCate(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return this.code;
  }
}
