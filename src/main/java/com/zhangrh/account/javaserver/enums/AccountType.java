package com.zhangrh.account.javaserver.enums;

public enum AccountType implements BaseCodeEnum{

  DefaultType(1), // 系统默认
  Extra(2); // 用户扩展

  private final int code;

  AccountType(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return this.code;
  }
}
