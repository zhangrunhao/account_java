package com.zhangrh.account.javaserver.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.zhangrh.account.javaserver.enums.TradeOperation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewTradeCateAccount extends AbstractEntity {
  private long tradeId;
  private long userId;
  private long accountId;
  private long tradeCateId;
  private BigDecimal money;
  private String remark;
  private LocalDate spendDate;
  private TradeOperation operate;
  private String accountName;
  private String tradeCateName;
}

