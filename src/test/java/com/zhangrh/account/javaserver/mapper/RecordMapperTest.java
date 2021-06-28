package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

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
    String remark = "西瓜";
    Record record = new Record();
    record.setUserId(userId);
    record.setRecordSortId(recordSortId);
    record.setAccountId(accountId);
    record.setRemark(remark);
    record.setCreateAt(new Date());
    recordMapper.insert(record);

    User user = new User();
    user.setUserId(userId);
    List<Record> records = recordMapper.selectListByUser(user);
    Record lastRecord = records.get(records.size() - 1);

    assertEquals(userId, lastRecord.getUserId());
    assertEquals(recordSortId, lastRecord.getRecordSortId());
    assertEquals(accountId, lastRecord.getAccountId());
    assertEquals(remark, lastRecord.getRemark());
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

    Record resultRecord = recordMapper.getRecordById(recordId);
    assertEquals(date.getTime(), record.getUpdateAt().getTime());
    assertEquals(record.getUpdateAt().getTime(), resultRecord.getUpdateAt().getTime());
    assertEquals(date.getTime(), resultRecord.getUpdateAt().getTime());

    assertEquals(record.getRecordId(), resultRecord.getRecordId());
    assertEquals(record.getAccountId(), resultRecord.getAccountId());
    assertEquals(record.getRemark(), resultRecord.getRemark());
    assertEquals(record.getUserId(), resultRecord.getUserId());
    assertEquals(record.getRecordSortId(), resultRecord.getRecordSortId());
  }
}
