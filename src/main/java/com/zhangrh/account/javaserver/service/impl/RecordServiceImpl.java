package com.zhangrh.account.javaserver.service.impl;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.service.RecordService;

import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

  @Override
  public void add(Record record) {
    
  }

  @Override
  public void update(Record record) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(Record record) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Record> getListByUser(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Record> getListByAccount(Account account) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Record> getListByTime(Date fromDate, Date toDate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Record getRecordById(long recordId) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
