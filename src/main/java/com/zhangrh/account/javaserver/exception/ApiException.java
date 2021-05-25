package com.zhangrh.account.javaserver.exception;

import com.zhangrh.account.javaserver.api.IErrorCode;

/**
 * 自定义api异常
 */
public class ApiException extends RuntimeException {
  private IErrorCode errorCode;

  public ApiException(IErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public IErrorCode getErrorCode() {
    return errorCode;
  }
}
