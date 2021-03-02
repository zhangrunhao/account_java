package com.zhangrh.account.javaserver.entity;

import java.sql.Timestamp;

/**
 * AbstractEntity
 */
public abstract class AbstractEntity {
  private long createAt;
  private long updateAt;
  private long deleteAt;

  public long getDeleteAt() {
    return deleteAt;
  }

  public void setDeleteAt(long deleteAt) {
    this.deleteAt = deleteAt;
  }

  public long getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(long updateAt) {
    this.updateAt = updateAt;
  }

  public String getCreateAt() {
    return new Timestamp(this.createAt).toString();
  }

  public void setCreateAt(long createAt) {
    this.createAt = createAt;
  }
}