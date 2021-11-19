package com.zhangrh.account.javaserver.service.Bo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.zhangrh.account.javaserver.entity.Trade;
import com.zhangrh.account.javaserver.enums.TradeOperation;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeBo extends AbstractBo{
  private long id;
  private long userId;
  private long accountId;
  private long tradeCateId;
  private BigDecimal money;
  private String remark;
  private LocalDate spendDate;
  private TradeOperation operate;

  public TradeBo() {
  }

  public TradeBo(Trade trade) {
    BeanUtils.copyProperties(trade, this);
  }

  public Trade toTrade() {
    Trade trade = new Trade();
    BeanUtils.copyProperties(this, trade);
    return trade;
  }
}
