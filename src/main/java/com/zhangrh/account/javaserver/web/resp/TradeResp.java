package com.zhangrh.account.javaserver.web.resp;

import java.time.LocalDate;

import com.zhangrh.account.javaserver.service.Bo.TradeBo;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeResp {
  private long id;
  private long accountId;
  private long tradeCateId;
  private String money;
  private String remark;
  private LocalDate spendDate;
  private int operate;
  private String tradeCateName;
  private String accountName;
  private String accountIcon;
  private String tradeCateIcon;

  public TradeResp() {
  }

  public TradeResp(TradeBo bo) {
    BeanUtils.copyProperties(bo, this);
    this.setId(bo.getTradeId());
    this.setMoney(bo.getMoney().toString());
    this.setTradeCateId(bo.getTradeCateId());
    this.setOperate(bo.getOperate().getCode());
  }
}
