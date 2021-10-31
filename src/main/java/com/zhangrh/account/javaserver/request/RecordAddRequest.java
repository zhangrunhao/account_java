package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RecordAddRequest {
  @Min(value = 1, message = "花费种类至少为1")
  private long recordSortId;
  // TODO: 如何判断数字类型的id不为空
  @Min(value = 1, message = "账户id至少为1")
  private long accountId;
  @NotEmpty(message = "备注不可为空")
  private String remark;
  @Min(value = 1, message = "花费时间至少为1")
  private long spendTimeStamp;
  @NotEmpty(message = "金额不可为空")
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
