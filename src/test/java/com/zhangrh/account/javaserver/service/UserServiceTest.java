package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zhangrh.account.javaserver.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  UserService userService;
  
  @Test
  void test() {
    User user = userService.getUserById(1);
    assertEquals("1", user.getUsersId());
    assertEquals("1970-01-01 08:00:00.0", user.getCreateAt());
    assertEquals("zhangrhweb@163.com", user.getEmail());
  }
}
