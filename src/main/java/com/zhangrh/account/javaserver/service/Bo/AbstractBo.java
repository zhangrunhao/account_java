package com.zhangrh.account.javaserver.service.Bo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractBo {
  private LocalDateTime createAt;
  private LocalDateTime updateAt;
  private LocalDateTime deleteAt;
}
