package com.zhangrh.account.javaserver.service;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;

public interface RecordService {

  void add(Record record);

  void update(Record record);

  void delete(Record record);

  List<Record> getListByUser(User user);

  List<Record> getListByAccount(Account account);
  
  List<Record> getListByAccountAndTime(Account account, Date fromDate, Date toDate);

  Record getRecordById(long recordId);

}
