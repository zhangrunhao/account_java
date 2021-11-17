package com.zhangrh.account.javaserver.web.resp;

import java.math.BigDecimal;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResp {
  private long id;
  private String icon;
  private String name;
  private int cate;
  private BigDecimal money;

  public AccountResp() {
  }

  public AccountResp(AccountBo bo) {
    setId(bo.getId());
    setIcon(bo.getIcon());
    setName(bo.getName());
    setCate(bo.getCate().getCode());
    setMoney(bo.getMoney());
  }
}

