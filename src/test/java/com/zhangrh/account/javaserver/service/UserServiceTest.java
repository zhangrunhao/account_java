package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  UserService userService;
  
  @Test
  void testGetUserFromEmail() {
    String emailFalse = "zhangrhweb@false.com";
    UserBo userFalse = userService.getUserFromEmail(emailFalse);
    assertNull(userFalse);

    String trueEmail = "zhangrhweb@163.com";
    UserBo trueUser = userService.getUserFromEmail(trueEmail);
    assertEquals(1, trueUser.getId());
  }

  @Test
  void testRegisterAndLogin() {
    // 测试注册
    String email = "test@test.com";
    String password = "test@test.com";
    userService.register(email, password);
    UserBo user = userService.getUserFromEmail(email);
    assertNotNull(user);

    // 测试登录
    String token = userService.login(email, password);
    assertTrue(JwtTokenUtil.verifyToken(token));
    String emailFromToken = JwtTokenUtil.getJwtValue(token, "email");
    assertEquals(email, emailFromToken);
  }
}
