package com.zhangrh.account.javaserver.response;

import java.util.List;

public class RecordDateGroupResponse {
  private String date;
  private List<RecordResponse> array;

  public RecordDateGroupResponse(String date, List<RecordResponse> list) {
    this.date = date;
    this.array = list;
  }

  public String getDate() {
    return date;
  }
  public List<RecordResponse> getArray() {
    return array;
  }
  public void setArray(List<RecordResponse> array) {
    this.array = array;
  }
  public void setDate(String date) {
    this.date = date;
  }
}
