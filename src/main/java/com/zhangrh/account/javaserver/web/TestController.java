package com.zhangrh.account.javaserver.web;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@RestController
public class TestController {

  @PostMapping("/session")
  @ResponseBody
  public String doSession() {
    return "测试";
  }
}
