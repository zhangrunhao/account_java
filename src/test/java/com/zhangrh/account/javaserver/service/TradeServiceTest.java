package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.zhangrh.account.javaserver.mapper")
public class TradeServiceTest {
  
  @Autowired
  TradeService tradeService;

  @Test
  void testAdd() {
    TradeBo tradeBo = new TradeBo();
    tradeBo.setAccountId(2);
    tradeBo.setMoney(BigDecimal.valueOf(10));
    tradeBo.setOperate(TradeOperation.Lend);
    tradeBo.setSpendDate(LocalDate.now());
    tradeBo.setRemark("test");
    tradeBo.setTradeCateId(1);
    tradeBo.setUserId(1);
    tradeService.add(tradeBo);
  }

  @Test
  void testUpdate() {
    TradeBo tradeBo = new TradeBo();
    tradeBo.setId(3);
    tradeBo.setAccountId(2);
    tradeBo.setMoney(BigDecimal.valueOf(20));
    tradeBo.setOperate(TradeOperation.Lend);
    tradeBo.setSpendDate(LocalDate.now());
    tradeBo.setRemark("testUpdate");
    tradeBo.setTradeCateId(1);
    tradeBo.setUserId(1);
    tradeService.update(tradeBo);
  }

  @Test
  void testDelete() {
    TradeBo tradeBo = new TradeBo();
    tradeBo.setId(3);
    tradeService.delete(tradeBo);
  }

  @Test
  void testQueryByUser() {
    UserBo userBo = new UserBo(1);
    List<TradeBo> tradeBos = tradeService.list(userBo);
    assertEquals(1, tradeBos.size());
  }

  @Test
  void testQueryById() {
    TradeBo tradeBo = new TradeBo();
    tradeBo.setId(1);
    tradeBo = tradeService.query(tradeBo);
    assertTrue(new BigDecimal(33).equals(tradeBo.getMoney()));
  }

  @Test
  void testQueryByAccount() {
    AccountBo accountBo = new AccountBo();
    accountBo.setId(2);
    List<TradeBo> tradeBos = tradeService.list(accountBo);
    assertEquals(1, tradeBos.size());
  }
}
