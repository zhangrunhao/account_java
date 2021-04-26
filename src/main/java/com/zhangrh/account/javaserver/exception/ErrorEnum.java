package com.zhangrh.account.javaserver.exception;

public enum ErrorEnum {
  SUCCESS(200, "成功"),
  NO_PERMISSION(403, "没有权限"),
  NO_AUTH(401, "需要登录"),
  NOT_FOUND(404, "未找到资源"),
  INTERNAL_SERVER_ERROR(500, "服务器错误");

  private Integer errorCode;
  private String errorMsg;
  private ErrorEnum(Integer errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }
  public String getErrorMsg() {
    return errorMsg;
  }
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  public Integer getErrorCode() {
    return errorCode;
  }
  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

}
