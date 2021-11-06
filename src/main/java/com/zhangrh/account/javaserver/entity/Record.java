package com.zhangrh.account.javaserver.entity;

public class Record extends RecordBase {
  private String sortName;
  private String accountName;
  public String getSortName() {
    return sortName;
  }
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
  public void setSortName(String sortName) {
    this.sortName = sortName;
  }
}
