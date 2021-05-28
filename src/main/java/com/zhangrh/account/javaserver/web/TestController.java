package com.zhangrh.account.javaserver.web;


import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

  @RequestMapping(value = "/getToken", method = RequestMethod.GET)
  public String doGetToken() {
    return JwtTokenUtil.generateToken("zhangrhweb@163.com");
  }

  @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
  public boolean doVerifyToken(
    @RequestParam String token
  ) {
    return JwtTokenUtil.verifyToken(token);
  }
}
