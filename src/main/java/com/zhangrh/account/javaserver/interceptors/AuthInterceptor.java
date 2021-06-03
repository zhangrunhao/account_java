package com.zhangrh.account.javaserver.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Authorization");
    if (!StringUtils.isEmpty(token)) { // 验证是否为空
      if (JwtTokenUtil.verifyToken(token)) { // 验证通过
        System.out.println(token);
      }
    }
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
}
