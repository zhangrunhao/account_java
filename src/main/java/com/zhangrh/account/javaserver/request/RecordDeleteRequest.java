package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;

public class RecordDeleteRequest {

  @Min(value = 1, message = "recordId至少为1")
  private long recordId;

  public long getRecordId() {
    return recordId;
  }

  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }
}
