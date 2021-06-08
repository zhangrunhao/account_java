package com.zhangrh.account.javaserver.dto;

import javax.validation.constraints.NotNull;

public class AccountDeleteParam {
  @NotNull(message = "账户id不可为空")
  private Long accountId;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }
}
