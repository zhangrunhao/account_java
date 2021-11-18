package com.zhangrh.account.javaserver.web.resp;

import com.zhangrh.account.javaserver.service.Bo.TradeCateBo;

import lombok.Data;

@Data
public class TradeCateResp {
  private long id;
  private String name;
  private String icon;
  private int operate;

  public TradeCateResp() {
  }

  public TradeCateResp(TradeCateBo bo) {
    setId(bo.getTradeCateId());
    setIcon(bo.getIcon());
    setName(bo.getName());
    setOperate(bo.getOperate().getCode());
  }
}
