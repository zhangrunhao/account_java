package com.zhangrh.account.javaserver.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhangrh.account.javaserver.api.ResultCode;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.utils.JwtTokenUtil;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Autowired
  UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("Authorization");
    if (!StringUtils.isEmpty(token)) {
      if (JwtTokenUtil.verifyToken(token)) {
        String email = JwtTokenUtil.getJwtValue(token, "email");
        User user = userService.getUserFromEmail(email);
        if (user == null) Asserts.fail(ResultCode.UNAUTHORIZED);
        UserInfoUtil.setUser(user);
      } else {
        Asserts.fail(ResultCode.UNAUTHORIZED);
      }
    }
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
}
