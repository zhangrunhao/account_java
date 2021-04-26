package com.zhangrh.account.javaserver.exception;

import javax.servlet.http.HttpServletRequest;

import com.zhangrh.account.javaserver.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
  /**
   * 处理自定义异常
   */
  @ExceptionHandler(value = DefinitionException.class)
  @ResponseBody
  public Result<Object> bizExceptionHandler(HttpServletRequest request, DefinitionException e) {
    return Result.defineError(e);
  }


  /**
   * 处理其他异常
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Result<Object> exceptionHandler(HttpServletRequest req, Exception e) {
    return Result.otherError(ErrorEnum.INTERNAL_SERVER_ERROR);
  }
}
