package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.UserLoginRegisterRequest;
import com.zhangrh.account.javaserver.service.UserService;

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
    User user = userService.getUserFromEmail(email);
    userService.deleteUserById(user.getUserId());
    User user2 = userService.getUserFromEmail(email);
    assertEquals(null, user2);
  }

  @Test
  void testLogin() {
    UserLoginRegisterRequest userLoginRegisterRequest = new UserLoginRegisterRequest();
    String email = "zhangrhweb@163.com";
    String password = "zhang.22";
    userLoginRegisterRequest.setEmail(email);
    userLoginRegisterRequest.setPassword(password);
    CommonResult<Map<String, Object>> result = userController.doLogin(userLoginRegisterRequest);
    assertNotNull(result);
    assertEquals(200, result.getCode());
    assertTrue(result.getData().get("token") instanceof String);
  }
}
