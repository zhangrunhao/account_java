package com.zhangrh.account.javaserver.entity;

import com.zhangrh.account.javaserver.enums.AccountCate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends AbstractEntity{
  private long id;
  private long userId;
  private String name;
  private AccountCate cate;
  private String icon;
}
