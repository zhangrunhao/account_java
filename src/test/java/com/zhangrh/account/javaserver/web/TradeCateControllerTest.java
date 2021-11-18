package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.req.TradeCateAddReq;
import com.zhangrh.account.javaserver.web.req.TradeCateDeleteReq;
import com.zhangrh.account.javaserver.web.req.TradeCateUpdateReq;
import com.zhangrh.account.javaserver.web.resp.TradeCateResp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeCateControllerTest {

  @Autowired
  TradeCateController tradeCateController;

  @Test
  void testAdd() {
    UserInfoUtil.setUser(new UserBo(1));
    TradeCateAddReq req = new TradeCateAddReq();
    req.setIcon("Box");
    req.setName("礼物");
    req.setOperate(2);
    CommonResult<String> result = tradeCateController.doAdd(req);
    assertEquals(200, result.getCode());
  }

  @Test
  void testList() {
    UserInfoUtil.setUser(new UserBo(1));
    CommonResult<List<TradeCateResp>> result = tradeCateController.doList();
    assertEquals(200, result.getCode());
  }

  @Test
  void testUpdate() {
    UserInfoUtil.setUser(new UserBo(1));
    TradeCateUpdateReq req = new TradeCateUpdateReq();
    req.setId(3);
    req.setIcon("Book");
    req.setName("书籍");
    req.setOperate(2);
    CommonResult<String> result = tradeCateController.doUpdate(req);
    assertEquals(200, result.getCode());
  }

  @Test
  void testUpdateExtra() {
    UserInfoUtil.setUser(new UserBo(1));
    TradeCateUpdateReq req = new TradeCateUpdateReq();
    req.setId(8);
    req.setIcon("Book");
    req.setName("书籍");
    req.setOperate(2);
    CommonResult<String> result = tradeCateController.doUpdate(req);
    assertEquals(200, result.getCode());
  }
  
  @Test
  void testDelete() {
    UserInfoUtil.setUser(new UserBo(1));
    TradeCateDeleteReq req = new TradeCateDeleteReq();
    req.setId(8);
    CommonResult<String> result = tradeCateController.doDelete(req);
    assertEquals(200, result.getCode());
  }
}
