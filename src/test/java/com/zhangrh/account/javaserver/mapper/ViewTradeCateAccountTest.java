package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zhangrh.account.javaserver.entity.ViewTradeCateAccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewTradeCateAccountTest {

  @Autowired
  ViewTradeCateAccountMapper viewTradeCateAccountMapper;

  @Test
  void testQueryUserId() {
    List<ViewTradeCateAccount> result =  viewTradeCateAccountMapper.queryByUserId(1);
    assertTrue(result.size() > 0);
  }
}
