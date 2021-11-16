package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zhangrh.account.javaserver.enums.AccountCate;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
  
  @Autowired
  AccountService accountService;

  @Test
  void testAdd() {
    AccountBo accountBo = new AccountBo();
    accountBo.setUserId(1);
    accountBo.setName("微信");
    accountBo.setCate(AccountCate.Property);
    accountBo.setIcon("Icon://image");
    accountService.add(accountBo);
  }

  @Test
  void testList() {
    List<AccountBo> accountBos = accountService.list(new UserBo(1));
    assertTrue(accountBos.size() > 0);
  }

  @Test
  void testUpdate() {
    AccountBo accountBo = new AccountBo();
    accountBo.setId(1);
    accountBo.setUserId(1);
    accountBo.setName("蚂蚁花呗");
    accountBo.setIcon("HB://img.png");
    accountBo.setCate(AccountCate.Debt);
    accountService.update(accountBo);
  }

  @Test
  void testDelete() {
    AccountBo accountBo = new AccountBo();
    accountBo.setId(2);
    accountService.delete(accountBo);
  }

  @Test
  void testGet() {
    AccountBo accountBo = new AccountBo();
    accountBo.setId(3);
    accountBo = accountService.get(accountBo);
    assertNotNull(accountBo.getUserId());
  }
}
