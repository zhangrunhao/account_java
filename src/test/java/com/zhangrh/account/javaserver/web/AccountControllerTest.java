package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.AccountAddRequest;
import com.zhangrh.account.javaserver.request.AccountUpdateRequest;
import com.zhangrh.account.javaserver.response.AccountResponse;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountControllerTest {
  
  @Autowired
  AccountController accountController;

  @Autowired
  AccountService accountService;

  @Test
  void testDoAdd() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    AccountAddRequest accountAddRequest = new AccountAddRequest();
    accountAddRequest.setColor("#fff");
    accountAddRequest.setIcon("www.baidu.com/1.png");
    accountAddRequest.setName("支付宝");
    accountAddRequest.setType("normal");
    CommonResult<String> result =  accountController.doAdd(accountAddRequest);
    assertEquals(200, result.getCode());
  }

  @Test
  void testDoList() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    CommonResult<List<AccountResponse>> result = accountController.doList();
    assertEquals(200, result.getCode());
    assertTrue(result.getData().size() > 0);
  }

  @Test
  void testDoUpdate() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    long accountId = 10;
    String color = "#fff";
    String name = "update";
    String type = "normal";
    String icon = "https://baidu.com/1.png" + Math.random();
    AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
    accountUpdateRequest.setAccountId(accountId);
    accountUpdateRequest.setColor(color);
    accountUpdateRequest.setName(name);
    accountUpdateRequest.setType(type);
    accountUpdateRequest.setIcon(icon);
    CommonResult<String> result =  accountController.doUpdate(accountUpdateRequest);
    assertEquals(200, result.getCode());
    
    Account account = accountService.getAccountByAccountId(user, accountId);
    assertEquals(icon, account.getIcon());
  }

  @Test
  void testDoGetAccount() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    long accountId = 10;
    CommonResult<AccountResponse> result = accountController.doGetAccount(accountId);
    assertNotNull(result.getData());
    String color = "#fff";
    assertEquals(color, result.getData().getColor());
  }
}
