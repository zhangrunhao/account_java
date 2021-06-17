package com.zhangrh.account.javaserver.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest {

  @Autowired
  UserController userController;

  @Test
  void testRegister() {
    // UserLoginRegisterRequest userLoginRegisterRequest = new UserLoginRegisterRequest();
    // userLoginRegisterRequest.setEmail("ccc@11.com");
    // userLoginRegisterRequest.setPassword("zzz");
    // userController.doLogin()
  }
}
