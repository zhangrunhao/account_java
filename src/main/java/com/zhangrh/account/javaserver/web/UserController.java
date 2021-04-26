package com.zhangrh.account.javaserver.web;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/signin")
  public Result<Object> doSignin(
    @RequestBody User user
  ) {
    Object data = userService.signin(user.getEmail(), user.getPassword()).get("User");
    Result<Object> result = new Result<>();
    result.setCode(200);
    result.setSuccess(true);
    result.setData(data);
    result.setMsg("用户信息");
    return result;
  }
}
