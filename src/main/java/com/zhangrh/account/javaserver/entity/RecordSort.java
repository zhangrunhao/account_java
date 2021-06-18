package com.zhangrh.account.javaserver.entity;

public class RecordSort extends AbstractEntity {
  private long recordSortId;
  private long userId;
  private String icon;
  private String type;
  private String name;
  public String getName() {
    return name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public long getUserId() {
    return userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }
  public long getRecordSortId() {
    return recordSortId;
  }
  public void setRecordSortId(long recordSortId) {
    this.recordSortId = recordSortId;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getIcon() {
    return icon;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
}
