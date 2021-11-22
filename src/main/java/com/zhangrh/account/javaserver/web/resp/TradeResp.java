package com.zhangrh.account.javaserver.web.resp;

import java.time.LocalDate;

import com.zhangrh.account.javaserver.service.Bo.TradeBo;

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

  public TradeResp() {
  }

  public TradeResp(TradeBo bo) {
    setId(bo.getId());
    setAccountId(bo.getAccountId());
    setTradeCateId(bo.getTradeCateId());
    setMoney(bo.getMoney().toString());
    setRemark(bo.getRemark());
    setSpendDate(bo.getSpendDate());
    setOperate(bo.getOperate().getCode());
    setTradeCateName(bo.getTradeCateName());
    setAccountName(bo.getAccountName());
  }
}
