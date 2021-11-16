package com.zhangrh.account.javaserver.service.Bo;

import java.math.BigDecimal;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.enums.AccountCate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBo extends AbstractBo{
  private long id;
  private long userId;
  private String name;
  private AccountCate cate;
  private String icon;
  private BigDecimal money;
  private List<TradeBo> trades;

  public AccountBo() {
  }

  public AccountBo(Account account) {
    setId(account.getId());
    setUserId(account.getUserId());
    setName(account.getName());
    setCate(account.getCate());
    setIcon(account.getIcon());
    setCreateAt(account.getCreateAt());
    setUpdateAt(account.getUpdateAt());
    setDeleteAt(account.getDeleteAt());
  }

  public static Account toAccountEntity(AccountBo accountBo) {
    Account account = new Account();
    account.setId(accountBo.getId());
    account.setUserId(accountBo.getUserId());
    account.setName(accountBo.getName());
    account.setCate(accountBo.getCate());
    account.setIcon(accountBo.getIcon());
    account.setCreateAt(accountBo.getCreateAt());
    account.setDeleteAt(accountBo.getDeleteAt());
    account.setUpdateAt(accountBo.getUpdateAt());
    return account;
  }
}
