package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class RecordUpdateRequest {
  @Min(value = 1, message = "recordId至少为1")
  private long recordId;

  @Min(value = 1, message = "usersId至少为1")
  private long userId;

  @Min(value = 1, message = "recordSortId至少为1")
  private long recordSortId;

  @Min(value = 1, message = "accountId至少为1")
  private long accountId;

  private String remark;

  @Min(value = 1, message = "spendTimeStamp至少为1")
  private long spendTimeStamp;

  @NotEmpty(message = "金额不能为空")
  private String count;

  public long getSpendTimeStamp() {
    return spendTimeStamp;
  }
  public String getCount() {
    return count;
  }
  public void setCount(String count) {
    this.count = count;
  }
  public long getRecordId() {
    return recordId;
  }
  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }
  public long getUserId() {
    return userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }
  public long getRecordSortId() {
    return recordSortId;
  }
  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
  public long getAccountId() {
    return accountId;
  }
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public void setSpendTimeStamp(long spendTimeStamp) {
    this.spendTimeStamp = spendTimeStamp;
  }
}
