package com.zhangrh.account.javaserver.entity;

import java.sql.Timestamp;

/**
 * AbstractEntity
 */
public abstract class AbstractEntity {
  private long createAt;
  private long updateAt;
  private long deleteAt;

  public String getDeleteAt() {
    if (this.deleteAt == 0) return "";
    return new Timestamp(this.deleteAt).toString();
  }

  public void setDeleteAt(long deleteAt) {
    this.deleteAt = deleteAt;
  }

  public String getUpdateAt() {
    if (this.updateAt == 0) return "";
    return new Timestamp(this.updateAt).toString();
  }

  public void setUpdateAt(long updateAt) {
    this.updateAt = updateAt;
  }

  public String getCreateAt() {
    if (this.createAt == 0) return "";
    return new Timestamp(this.createAt).toString();
  }

  public void setCreateAt(long createAt) {
    this.createAt = createAt;
  }
}