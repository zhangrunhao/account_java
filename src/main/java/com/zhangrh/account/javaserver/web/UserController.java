package com.zhangrh.account.javaserver.web;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.dto.UserLoginParam;
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
  public CommonResult<String> doHi() {
    return CommonResult.success("hi");
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<User> doSignin(
    @Validated @RequestBody UserLoginParam userLoginParam
  ) {
    User user = userService.login(userLoginParam.getEmail(), userLoginParam.getPassword());
    if (user == null) {
      return CommonResult.validateFailed("用户名者密码错误");
    }
    return CommonResult.success(user);
  }
}
