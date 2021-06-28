package com.zhangrh.account.javaserver.entity;

public class Record extends AbstractEntity {
  private long recordId;
  private long userId;
  private long recordSortId;
  private long accountId;
  private String remark;
  public long getRecordId() {
    return recordId;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public long getAccountId() {
    return accountId;
  }
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
  public long getRecordSortId() {
    return recordSortId;
  }
  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
  public long getUserId() {
    return userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }
  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }
}
