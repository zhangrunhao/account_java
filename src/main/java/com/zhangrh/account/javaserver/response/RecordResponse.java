package com.zhangrh.account.javaserver.response;

import java.math.BigDecimal;

import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.utils.DateTimeUtil;

public class RecordResponse {
  private long recordId;
  private long recordSortId;
  private long accountId;
  private String remark;
  private long spendTimeStamp;
  private BigDecimal count;
  private String type;


  public static RecordResponse recordEntityToRecordResponse(Record record) {
    RecordResponse response = new RecordResponse();
    response.setRecordId(record.getRecordId());
    response.setRecordSortId(record.getRecordSortId());
    response.setAccountId(record.getAccountId());
    response.setRemark(record.getRemark());
    response.setSpendTimeStamp(DateTimeUtil.LocalDateToMill(record.getSpendTime()));
    response.setCount(record.getCount());
    String type = (record.getCount().signum() == 1) ? "income" : "expend";
    response.setType(type);
    return response;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getCount() {
    return count;
  }

  public void setCount(BigDecimal count) {
    this.count = count;
  }

  public long getRecordId() {
    return recordId;
  }
  public long getSpendTimeStamp() {
    return spendTimeStamp;
  }
  public void setSpendTimeStamp(long spendTimeStamp) {
    this.spendTimeStamp = spendTimeStamp;
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
  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }
}
