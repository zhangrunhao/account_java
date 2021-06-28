package com.zhangrh.account.javaserver.entity;

import java.util.Date;

/**
 * AbstractEntity
 */
public abstract class AbstractEntity {
  private Date createAt;
  private Date updateAt;
  private Date deleteAt;
  public Date getCreateAt() {
    return createAt;
  }
  public Date getDeleteAt() {
    return deleteAt;
  }
  public void setDeleteAt(Date deleteAt) {
    this.deleteAt = deleteAt;
  }
  public Date getUpdateAt() {
    return updateAt;
  }
  public void setUpdateAt(Date updateAt) {
    this.updateAt = updateAt;
  }
  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }
}