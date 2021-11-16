package com.zhangrh.account.javaserver.enums;

public enum TradeCateType implements BaseCodeEnum{
  Default(1), // 默认
  Extra(2), // 扩展
  System(3); // 系统

  private final int code;

  TradeCateType(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return this.code;
  }
}
