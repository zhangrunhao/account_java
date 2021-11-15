package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.utils.Md5Util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

  @Autowired
  UserMapper userMapper;

  @Test
  void testInsert() {
    User user = new User();
    user.setEmail("zhangrhweb@163.com");
    user.setPassword(Md5Util.getMd5("zhang.22"));
    user.setCreateAt(LocalDateTime.now());
    int r = userMapper.insert(user);
    assertEquals(1, r);
  }

  @Test
  void testQueryId() {
    User user = userMapper.queryId(1);
    assertEquals("zhangrhweb@163.com", user.getEmail());
    assertEquals(Md5Util.getMd5("zhang.22"), user.getPassword());
  }

  @Test
  void testQueryEmail() {
    User user = userMapper.queryEmail("zhangrhweb@163.com");
    assertEquals(1, user.getId());
    assertEquals(Md5Util.getMd5("zhang.22"), user.getPassword());
  }
}
