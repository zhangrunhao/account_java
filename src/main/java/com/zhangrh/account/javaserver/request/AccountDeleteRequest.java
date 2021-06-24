package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;

public class AccountDeleteRequest {
  @Min(value = 1, message = "accountId至少为1")
  private Long accountId;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }
}
