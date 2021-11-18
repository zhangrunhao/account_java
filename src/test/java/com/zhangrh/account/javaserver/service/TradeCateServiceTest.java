package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.mapper.UserToTradeCateMapper;
import com.zhangrh.account.javaserver.service.Bo.TradeCateBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeCateServiceTest {

  @Autowired
  TradeCateService tradeCateService;

  @Autowired
  UserToTradeCateMapper userToTradeCateMapper;

  @Test
  void testAdd() {
    TradeCateBo tradeCateBo = new TradeCateBo();
    tradeCateBo.setUserId(1);
    tradeCateBo.setIcon("Car");
    tradeCateBo.setName("交通");
    tradeCateBo.setOperate(TradeOperation.Expend);
    tradeCateService.add(tradeCateBo);
  }

  @Test
  void testList() {
    UserBo userBo = new UserBo(1);
    List<TradeCateBo> list = tradeCateService.list(userBo);
    assertTrue(list.size() > 0);
  }

  @Test
  void testUpdateExtra() {
    TradeCateBo tradeCateBo = new TradeCateBo();
    tradeCateBo.setTradeCateId(2);
    tradeCateBo.setUserId(1);
    tradeCateBo.setIcon("Update");
    tradeCateBo.setName("吃饭");
    tradeCateBo.setOperate(TradeOperation.Expend);

    tradeCateService.update(tradeCateBo);
  }

  
}
