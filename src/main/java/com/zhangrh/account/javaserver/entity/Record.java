package com.zhangrh.account.javaserver.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Record extends AbstractEntity {
  private long recordId;
  private long userId;
  private long recordSortId;
  private long accountId;
  private String remark;
  private LocalDate spendTime;
  private BigDecimal count;
  public long getRecordId() {
    return recordId;
  }
  public BigDecimal getCount() {
    return count;
  }
  public void setCount(BigDecimal count) {
    this.count = count;
  }
  public LocalDate getSpendTime() {
    return spendTime;
  }
  public void setSpendTime(LocalDate spendTime) {
    this.spendTime = spendTime;
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
