package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToAccount extends AbstractEntity {
  private long id;
  private long userId;
  private long accountId;
}
