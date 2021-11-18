package com.zhangrh.account.javaserver.web;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.web.req.UserLoginRegisterRequest;

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

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doRegister(
    @Validated @RequestBody UserLoginRegisterRequest userRegisterRequest
  ) {
    try {
      userService.register(userRegisterRequest.getEmail(), userRegisterRequest.getPassword());
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("注册成功");
  }


  /**
   * 用户登录
   * @param userLoginParam
   * @return token
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doLogin(
    @Validated @RequestBody UserLoginRegisterRequest userLoginRequest
  ) {
    String token = null;
    try {
      token = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    if (token == null) {
      return CommonResult.failed("token is null");
    } else {
      return CommonResult.success(token);
    }
  }
}
