package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeCate extends AbstractEntity {
  private long id;
  private String name;
  private String icon;
  private String type;
  private String operate;
}
