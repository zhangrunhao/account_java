package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.entity.UserToTradeCate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserToTraceCateMapperTest {

  @Autowired
  UserToTradeCateMapper userToTradeCateMapper;

  @Test
  void testInsert() {
    UserToTradeCate userToTradeCate = new UserToTradeCate();
    userToTradeCate.setCreateAt(LocalDateTime.now());
    userToTradeCate.setUserId(1);
    userToTradeCate.setTradeCateId(1);
    int i = userToTradeCateMapper.insert(userToTradeCate);
    assertEquals(1, i);
  }

  @Test
  void testQueryUser() {
    User user = new User();
    user.setId(1);
    List<UserToTradeCate> list = userToTradeCateMapper.queryUser(user);
    assertTrue(list.size() > 0);
  }

  @Test
  void testDelete() {
    UserToTradeCate userToTradeCate = new UserToTradeCate();
    userToTradeCate.setId(1);
    userToTradeCate.setDeleteAt(LocalDateTime.now());
    int r = userToTradeCateMapper.delete(userToTradeCate);
    assertEquals(1, r);
  }
}
