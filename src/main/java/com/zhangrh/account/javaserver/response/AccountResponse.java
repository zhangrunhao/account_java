package com.zhangrh.account.javaserver.response;

import java.math.BigDecimal;

import com.zhangrh.account.javaserver.enums.AccountCate;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
  private long id;
  private String icon;
  private String name;
  private AccountCate cate;
  private BigDecimal money;

  public AccountResponse() {
  }

  public AccountResponse(AccountBo bo) {
    setId(bo.getId());
    setIcon(bo.getIcon());
    setName(bo.getName());
    setCate(bo.getCate());
    setMoney(bo.getMoney());
  }
}

