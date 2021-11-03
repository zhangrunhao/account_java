package com.zhangrh.account.javaserver.entity;

import java.time.LocalDateTime;

/**
 * AbstractEntity
 */
public abstract class AbstractEntity {
  private LocalDateTime createAt;
  private LocalDateTime updateAt;
  private LocalDateTime deleteAt;

  public LocalDateTime getCreateAt() {
    return createAt;
  }
  public LocalDateTime getDeleteAt() {
    return deleteAt;
  }
  public void setDeleteAt(LocalDateTime deleteAt) {
    this.deleteAt = deleteAt;
  }
  public LocalDateTime getUpdateAt() {
    return updateAt;
  }
  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }
  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }
}