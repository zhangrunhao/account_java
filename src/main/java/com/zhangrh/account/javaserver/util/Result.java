package com.zhangrh.account.javaserver.util;

import com.zhangrh.account.javaserver.exception.DefinitionException;
import com.zhangrh.account.javaserver.exception.ErrorEnum;

public class Result<T> {
  private Boolean success; // 是否成功
  private Integer code; // 状态码
  private String msg; // 提示信息
  private T data; // 数据
  public Result() {
  }

  // 自定义返回结果的构造方法
  public Result(Boolean success, Integer code, String msg, T data) {
    this.setSuccess(success);
    this.setCode(code);
    this.setMsg(msg);
    this.setData(data);
  }

  // 自定义异常返回的结果
  public static Result<Object> defineError(DefinitionException de) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setCode(de.getErrorCode());
    result.setMsg(de.getErrorMsg());
    result.setData(null);
    return result;
  }

  // 其他异常返回的结果
  public static Result<Object> otherError(ErrorEnum errorEnum) {
    Result<Object> result = new Result<>();
    result.setMsg(errorEnum.getErrorMsg());
    result.setCode(errorEnum.getErrorCode());
    result.setSuccess(false);
    result.setData(null);
    return result;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}
