package com.zhangrh.account.javaserver.request;

import javax.validation.constraints.NotNull;

public class RecordSortDeleteRequest {
  @NotNull(message = "需要删除的种类id不可为空")
  private long recordSortId;

  public long getRecordSortId() {
    return recordSortId;
  }

  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
}
