package com.zhangrh.account.javaserver.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends AbstractEntity {
  private long id;
  private String email;
  private String password;
}
