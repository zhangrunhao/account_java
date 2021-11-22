package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import com.zhangrh.account.javaserver.entity.TradeCate;
import com.zhangrh.account.javaserver.enums.TradeCateType;
import com.zhangrh.account.javaserver.enums.TradeOperation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TradeCateMapperTest {
  
  @Autowired
  TradeCateMapper tradeCateMapper;

  @Test
  void testInsert() {
    TradeCate tradeCate = new TradeCate();
    tradeCate.setCreateAt(LocalDateTime.now());
    tradeCate.setIcon("trade://img.png");
    tradeCate.setName("交通");
    tradeCate.setType(TradeCateType.Default);
    tradeCate.setOperate(TradeOperation.Expend);
    int r = tradeCateMapper.insert(tradeCate);
    assertEquals(1, r);
    assertNotNull(tradeCate.getId());
  }

  @Test
  void testQuery() {
    TradeCate tradeCate = tradeCateMapper.queryId(new TradeCate(1));
    assertEquals("交通", tradeCate.getName());
    assertEquals(TradeOperation.Expend, tradeCate.getOperate());
  }

  @Test
  void testUpdate() {
    TradeCate tradeCate = new TradeCate(1);
    tradeCate.setUpdateAt(LocalDateTime.now());
    tradeCate.setIcon("trade://test.png");
    tradeCate.setName("工资");
    tradeCate.setType(TradeCateType.Default);
    tradeCate.setOperate(TradeOperation.Income);
    int res = tradeCateMapper.update(tradeCate);
    assertEquals(1, res);
  }

  @Test
  void testDelete() {
    TradeCate tradeCate = new TradeCate(1);
    tradeCate.setDeleteAt(LocalDateTime.now());
    int r = tradeCateMapper.delete(tradeCate);
    assertEquals(1, r);
  }

  @Test
  void testQueryOperate() {
    TradeCate tradeCate = tradeCateMapper.queryOperate(TradeOperation.Receive.getCode());
    assertEquals(15, tradeCate.getId());
  }
}
