package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.enums.AccountCate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountMapperTest {

  @Autowired
  AccountMapper accountMapper;

  private String icon = "http://pic.616pic.com/ys_img/00/03/78/04RotuWM2Y.jpg";
  private String name = "微信";
  private AccountCate cate = AccountCate.Property;


  @Test
  void testInsert() {
    Account account = new Account();
    account.setUserId(1);
    account.setIcon(icon);
    account.setName(name);
    account.setCate(cate);
    account.setCreateAt(LocalDateTime.now());
    int res = accountMapper.insert(account);
    assertEquals(1, res);
    assertNotNull(account.getId());
  }

  @Test
  void testQueryId() {
    long id = 1;
    Account account =  accountMapper.queryId(id);
    assertEquals(1, account.getUserId());
    assertEquals(account.getName(), name);
    assertEquals(account.getIcon(), icon);
    assertEquals(account.getCate(), cate);
    assertNotNull(account.getCreateAt());
  }

  @Test
  void testUpdate() {
    Account account = new Account();
    account.setId(1);
    account.setIcon("icon");
    account.setName(name);
    account.setCate(cate);
    account.setUpdateAt(LocalDateTime.now());
    int r = accountMapper.update(account);
    assertEquals(r, 1);
  }

  @Test
  void testDelete() {
    Account account = new Account();
    account.setId(1);
    account.setDeleteAt(LocalDateTime.now());
    int r = accountMapper.delete(account);
    assertEquals(1, r);
  }
}
