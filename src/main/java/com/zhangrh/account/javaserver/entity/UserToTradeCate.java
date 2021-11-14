package com.zhangrh.account.javaserver.entity;

import lombok.Data;

@Data
public class UserToTradeCate {
  private long id;
  private long userId;
  private long tradeCateId;
}
