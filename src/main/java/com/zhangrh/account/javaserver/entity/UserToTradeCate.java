package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToTradeCate {
  private long id;
  private long userId;
  private long tradeCateId;
}
