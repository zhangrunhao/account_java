package com.zhangrh.account.javaserver.web;

import java.util.Map;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/register")
  public User doRegister(
    @RequestParam("email") String email,
    @RequestParam("password") String password
  ) {
    return userService.register(email, password);
  }

  @PostMapping("/signin")
  public Map<String, Object> doSignin(
    @RequestBody User user
    // @RequestParam("email") String email,
    // @RequestParam("password") String password
  ) {
    return userService.signin(user.getEmail(), user.getPassword());
  }

}
