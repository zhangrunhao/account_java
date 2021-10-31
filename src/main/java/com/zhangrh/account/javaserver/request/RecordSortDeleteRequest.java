package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.Min;

public class RecordSortDeleteRequest {

  @Min(value = 1, message = "不可为空")
  private long recordSortId;

  public long getRecordSortId() {
    return recordSortId;
  }

  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
}
