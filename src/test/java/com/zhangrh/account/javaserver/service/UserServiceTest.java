package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zhangrh.account.javaserver.entity.User;
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
    User userFalse = userService.getUserFromEmail(emailFalse);
    assertNull(userFalse);

    String trueEmail = "zhangrhweb@163.com";
    User trueUser = userService.getUserFromEmail(trueEmail);
    assertEquals(1, trueUser.getId());
  }

  @Test
  void testRegisterAndLoginAndDeleteById() {
    // 测试注册
    String email = "test@test.com";
    String password = "test@test.com";
    userService.register(email, password);
    User user = userService.getUserFromEmail(email);
    assertNotNull(user);

    // 测试登录
    String token = userService.login(email, password);
    assertTrue(JwtTokenUtil.verifyToken(token));
    String emailFromToken = JwtTokenUtil.getJwtValue(token, "email");
    assertEquals(email, emailFromToken);
  }
}
