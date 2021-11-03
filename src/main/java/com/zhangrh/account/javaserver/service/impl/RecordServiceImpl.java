package com.zhangrh.account.javaserver.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.RecordMapper;
import com.zhangrh.account.javaserver.service.RecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);

  @Autowired
  RecordMapper recordMapper;

  private static List<Record> filterDeletedData(List<Record> queryList) {
    List<Record> list = new ArrayList<>();
    for (Record record: queryList) {
      if (record.getDeleteAt() == null) {
        list.add(record);
      }
    }
    return list;
  }

  @Override
  public void add(Record record) {
    try {
      record.setCreateAt(LocalDateTime.now());
      recordMapper.insert(record);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录创建失败");
    }
  }

  @Override
  public void update(Record record) {
    try {
      record.setUpdateAt(LocalDateTime.now());
      int size = recordMapper.update(record);
      if (size != 1) throw new Exception("record update row size is not 1");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录更新失败");
    }
  }

  @Override
  public void delete(Record record) {
    try {
      record.setDeleteAt(LocalDateTime.now());
      int size = recordMapper.delete(record);
      if (size != 1) throw new Exception("record delete row size is not 1");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录删除失败");
    }
  }

  @Override
  public List<Record> getListByUser(User user) {
    List<Record> list = null;
    try {
      List<Record> queryList = recordMapper.queryListByUser(user);
      list = filterDeletedData(queryList);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询用户收支类表出错");
    }
    return list;
  }

  @Override
  public List<Record> getListByAccount(Account account) {
    List<Record> list = null;
    try {
      List<Record> queryList = recordMapper.queryListByAccount(account);
      list = filterDeletedData(queryList);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询用户收支类表出错");
    }
    return list;
  }

  @Override
  public Record getRecordById(long recordId) {
    Record record = null;
    try {
      record = recordMapper.queryRecordById(recordId);
      if (record.getDeleteAt() != null) return null;
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录查询失败");
    }
    return record;
  }

  @Override
  public Map<String, List<Record>> getDateGroupRecordByUser(User user) {
    List<Record> records = null;
    try {
      List<Record> qList = recordMapper.queryListByUser(user);
      records = filterDeletedData(qList);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询用户收支类表出错");
    }
    return recordsSortGroupByDate(records);
  }

  private Map<String, List<Record>> recordsSortGroupByDate(List<Record> records) {
    Map<String, List<Record>> result = new HashMap<>();
    for (Record record : records) {
      LocalDate spendDate = record.getSpendTime();
      String dateStr = spendDate.toString();
      List<Record> recordList = result.get(dateStr);
      if (recordList == null) {
        recordList = new ArrayList<>();
        recordList.add(record);
        result.put(dateStr, recordList);
      } else {
        recordList.add(record);
      }
    }
    return result;
  }
}
