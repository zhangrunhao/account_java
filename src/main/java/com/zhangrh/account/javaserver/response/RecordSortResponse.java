package com.zhangrh.account.javaserver.response;

public class RecordSortResponse {
  private long RecordSortId;
  private String name;
  private String icon;
  private String type;
  public long getRecordSortId() {
    return RecordSortId;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setRecordSortId(long recordSortId) {
    this.RecordSortId = recordSortId;
  }
}
