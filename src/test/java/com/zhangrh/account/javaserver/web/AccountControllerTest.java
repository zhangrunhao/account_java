package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.req.AccountAddReq;
import com.zhangrh.account.javaserver.web.req.AccountDeleteReq;
import com.zhangrh.account.javaserver.web.req.AccountUpdateReq;
import com.zhangrh.account.javaserver.web.resp.AccountResp;

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
    UserInfoUtil.setUser(new UserBo(1));
    AccountAddReq req = new AccountAddReq();
    req.setName("微信");
    req.setIcon("http://pic.616pic.com/ys_bnew_img/00/13/14/56S1GVSgRJ.jpg");
    req.setCate(1);
    CommonResult<String> result =  accountController.doAdd(req);
    assertEquals(200, result.getCode());
  }

  @Test
  void testDoList() {
    UserInfoUtil.setUser(new UserBo(1));
    CommonResult<List<AccountResp>> result = accountController.doList();
    assertEquals(200, result.getCode());
    assertTrue(result.getData().size() > 0);
  }

  @Test
  void testDoUpdate() {
    UserInfoUtil.setUser(new UserBo(1));
    AccountUpdateReq req = new AccountUpdateReq();
    req.setId((long) 5);
    req.setCate(2);
    req.setName("微信");
    req.setIcon("http://pic.616pic.com/ys_bnew_img/00/13/14/56S1GVSgRJ.jpg");

    CommonResult<String> result =  accountController.doUpdate(req);
    assertEquals(200, result.getCode());
  }

  @Test
  void testDoDelete() {
    AccountDeleteReq req = new AccountDeleteReq();
    req.setId((long) 6);
    CommonResult<String> result = accountController.doDelete(req);
    assertEquals(200, result.getCode());
  }

  @Test
  void testDoGetAccount() {
    CommonResult<AccountResp> result = accountController.doGetAccount(6);
    assertEquals(200, result.getCode());
  }
}
