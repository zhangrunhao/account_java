package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordMapperTest {
  
  @Autowired
  RecordMapper recordMapper;

  @Test
  void testAdd() {
    long userId = 1;
    long recordSortId = 2;
    long accountId = 3;
    Date date = new Date();
    String remark = "看电影";
    Record record = new Record();
    record.setUserId(userId);
    record.setRecordSortId(recordSortId);
    record.setAccountId(accountId);
    record.setRemark(remark);
    record.setCreateAt(date);
    record.setSpendTime(date);
    recordMapper.insert(record);

    User user = new User();
    user.setUserId(userId);
    List<Record> records = recordMapper.queryListByUser(user);
    Record lastRecord = records.get(records.size() - 1);

    assertEquals(userId, lastRecord.getUserId());
    assertEquals(recordSortId, lastRecord.getRecordSortId());
    assertEquals(accountId, lastRecord.getAccountId());
    assertEquals(remark, lastRecord.getRemark());
    assertEquals(date.getTime(), lastRecord.getSpendTime().getTime());

    lastRecord.setDeleteAt(new Date());
    int deleteCount = recordMapper.delete(lastRecord);
    assertEquals(1, deleteCount);

    Record deletedRecord = recordMapper.queryRecordById(lastRecord.getRecordId());
    assertNotNull(deletedRecord.getDeleteAt());
  }

  @Test
  void testUpdate() {
    long recordId = 2;
    long userId = 12;
    long accountId = 4;
    long recordSortId = 8;
    Date date = new Date();
    String remark = "滴滴打车";

    Record record = new Record();
    record.setRecordId(recordId);
    record.setAccountId(accountId);
    record.setUserId(userId);
    record.setRecordSortId(recordSortId);
    record.setRemark(remark);
    record.setUpdateAt(date);

    int count = recordMapper.update(record);
    assertEquals(1, count);

    Record resultRecord = recordMapper.queryRecordById(recordId);
    assertEquals(date.getTime(), record.getUpdateAt().getTime());
    assertEquals(record.getUpdateAt().getTime(), resultRecord.getUpdateAt().getTime());
    assertEquals(date.getTime(), resultRecord.getUpdateAt().getTime());

    assertEquals(record.getRecordId(), resultRecord.getRecordId());
    assertEquals(record.getAccountId(), resultRecord.getAccountId());
    assertEquals(record.getRemark(), resultRecord.getRemark());
    assertEquals(record.getUserId(), resultRecord.getUserId());
    assertEquals(record.getRecordSortId(), resultRecord.getRecordSortId());
  }

  @Test
  void testQueryByTime() {
    Account account = new Account();
    account.setUserId(1);
    account.setAccountId(3);
    Date fromDate = new Date(0);
    Date toDate = new Date();
    List<Record> records = recordMapper.queryListByAccountAndTime(account, fromDate, toDate);
    assertTrue(records.size() > 0);
  }
}
