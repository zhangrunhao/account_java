package com.zhangrh.account.javaserver.entity;

import com.zhangrh.account.javaserver.enums.TradeCateType;
import com.zhangrh.account.javaserver.enums.TradeOperation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeCate extends AbstractEntity {
  private long id;
  private String name;
  private String icon;
  private TradeCateType type;
  private TradeOperation operate;

  public TradeCate() {
  }

  public TradeCate(long id) {
    setId(id);
  }
}
