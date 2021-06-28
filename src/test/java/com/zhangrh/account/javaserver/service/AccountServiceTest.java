package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {
  
  @Autowired
  AccountService accountService;

  @Test
  void testAddAndListAndDelete() {
    User user = new User();
    user.setUserId(1);
    Account account = new Account();
    account.setUserId(user.getUserId());
    account.setColor("#fff");
    account.setIcon("http://aaa.com/b.png");
    account.setName("testName");
    account.setType("normal");
    accountService.add(user, account);

    List<Account> list = accountService.list(user);
    Account account1 = list.get(list.size() - 1);
    assertEquals(account.getAccountId(), account1.getAccountId());

    Account account2 = accountService.getAccountByAccountId(user, account.getAccountId());
    assertEquals(account.getAccountId(), account2.getAccountId());

    accountService.delete(user, account);
    try {
      accountService.getAccountByAccountId(user, account.getAccountId());
    } catch (Exception e) {
      assertEquals("账户信息查询失败", e.getMessage());
    }
  }

  @Test
  void testUpdate() {
    User user = new User();
    user.setUserId(1);
    Account account = new Account();
    account.setUserId(user.getUserId());
    account.setColor("#fff");
    account.setIcon("http://aaa.com/b.png");
    account.setName("testName");
    account.setType("normal");
    accountService.add(user, account);

    Account account1 = new Account();
    String account1Name = "update" + Math.random();
    BeanUtils.copyProperties(account, account1);
    account1.setName(account1Name);
    accountService.update(user, account1);

    Account account2 = accountService.getAccountByAccountId(user, account1.getAccountId());
    assertEquals(account1Name, account2.getName());
  }
}
