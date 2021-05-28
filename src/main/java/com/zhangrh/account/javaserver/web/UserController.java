package com.zhangrh.account.javaserver.web;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.dto.UserLoginParam;
import com.zhangrh.account.javaserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doLogin(
    @Validated @RequestBody UserLoginParam userLoginParam
  ) {
    String token = null;
    try {
      token = userService.login(userLoginParam.getEmail(), userLoginParam.getPassword());
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(token);
  }
}
