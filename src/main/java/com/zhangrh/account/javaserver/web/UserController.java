package com.zhangrh.account.javaserver.web;

import java.util.HashMap;
import java.util.Map;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.dto.UserParam;
import com.zhangrh.account.javaserver.entity.User;
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

  @RequestMapping(value = "/hi", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doHi() {
    return CommonResult.success("测试");
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<User> doRegister(
    @Validated @RequestBody UserParam userRegisterParam
  ) {
    User user = null;
    try {
      user = userService.register(userRegisterParam.getEmail(), userRegisterParam.getPassword());
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(user);
  }


  /**
   * 用户登录
   * @param userLoginParam
   * @return token
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<Map<String, Object>> doLogin(
    @Validated @RequestBody UserParam userLoginParam
  ) {
    String token = null;
    try {
      token = userService.login(userLoginParam.getEmail(), userLoginParam.getPassword());
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    Map<String, Object> map = new HashMap<>();
    map.put("token", token);
    return CommonResult.success(map);
  }
}
