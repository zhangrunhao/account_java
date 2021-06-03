package com.zhangrh.account.javaserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1) // 数字越小, 优先执行
@Component
@WebFilter(filterName = "MyFilter", urlPatterns = {"/**"}) // 过滤所有规则
public class MyFilter implements Filter{

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
        // HttpServletRequest request = (HttpServletRequest) req;
        // HttpServletResponse response = (HttpServletResponse) resp;
        // String token = request.getHeader("Authorization");
        // boolean flag = JwtTokenUtil.verifyToken(token);
        // System.out.println(flag);
        // chain.doFilter(request, response);
        chain.doFilter(req, resp);
  }
  
}
