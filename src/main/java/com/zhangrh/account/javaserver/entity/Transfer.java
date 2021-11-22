package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transfer extends AbstractEntity {
  private long id;
  private long outTradeId;
  private long inTradeId;
}
