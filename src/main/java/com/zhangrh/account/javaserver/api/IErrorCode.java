package com.zhangrh.account.javaserver.api;

/**
 * 封装api的错误码
 */
public interface IErrorCode {
  long getCode();
  String getMessage();
}
