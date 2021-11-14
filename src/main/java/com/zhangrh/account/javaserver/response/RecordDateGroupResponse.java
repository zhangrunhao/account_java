package com.zhangrh.account.javaserver.response;

import java.time.LocalDate;
import java.util.List;

import com.zhangrh.account.javaserver.utils.DateTimeUtil;

public class RecordDateGroupResponse implements Comparable<RecordDateGroupResponse> {
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

  @Override
  public int compareTo(RecordDateGroupResponse o) {
    long oMill = DateTimeUtil.LocalDateToMill(LocalDate.parse(o.date));
    long thisMill = DateTimeUtil.LocalDateToMill(LocalDate.parse(this.date));
    if ((oMill - thisMill) < 0) {
      return -1;
    } else {
      return 1;
    }
  }
}
