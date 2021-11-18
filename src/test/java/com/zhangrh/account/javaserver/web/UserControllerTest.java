package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.web.req.UserLoginRegisterRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {

  @Autowired
  UserController userController;

  @Autowired
  UserService userService;

  @Test
  void testRegister() {
    UserLoginRegisterRequest userLoginRegisterRequest = new UserLoginRegisterRequest();
    String email = "ccc@11.com";
    userLoginRegisterRequest.setEmail(email);
    userLoginRegisterRequest.setPassword("zzz");
    CommonResult<String> result =  userController.doRegister(userLoginRegisterRequest);
    assertEquals(200, result.getCode());
  }

  @Test
  void testLogin() {
    UserLoginRegisterRequest userLoginRegisterRequest = new UserLoginRegisterRequest();
    String email = "zhangrhweb@163.com";
    String password = "zhang.22";
    userLoginRegisterRequest.setEmail(email);
    userLoginRegisterRequest.setPassword(password);
    CommonResult<String> result = userController.doLogin(userLoginRegisterRequest);
    assertEquals(200, result.getCode());
    assertTrue(result.getData() instanceof String);
  }
}
