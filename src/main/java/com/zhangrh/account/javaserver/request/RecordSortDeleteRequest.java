package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;

public class RecordSortDeleteRequest {

  @Min(value = 1, message = "recordSortId至少为1")
  private long recordSortId;

  public long getRecordSortId() {
    return recordSortId;
  }

  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
}
