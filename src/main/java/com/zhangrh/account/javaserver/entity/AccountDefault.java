package com.zhangrh.account.javaserver.entity;

import com.zhangrh.account.javaserver.enums.AccountCate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDefault extends AbstractEntity {
  private long id;
  private String name;
  private AccountCate cate;
  private String icon;
}
