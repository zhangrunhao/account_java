package com.zhangrh.account.javaserver.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.utils.DateTimeUtil;

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
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = LocalDate.now();
    String remark = "看电影";
    Record record = new Record();
    BigDecimal count = new BigDecimal("-22.22");
    record.setUserId(userId);
    record.setRecordSortId(recordSortId);
    record.setAccountId(accountId);
    record.setRemark(remark);
    record.setCreateAt(localDateTime);
    record.setSpendTime(localDate);
    record.setCount(count);
    recordMapper.insert(record);

    User user = new User();
    user.setUserId(userId);
    List<Record> records = recordMapper.queryListByUser(user);
    Record lastRecord = records.get(records.size() - 1);

    assertEquals(userId, lastRecord.getUserId());
    assertEquals(recordSortId, lastRecord.getRecordSortId());
    assertEquals(accountId, lastRecord.getAccountId());
    assertEquals(remark, lastRecord.getRemark());
    assertEquals(localDate, lastRecord.getSpendTime());
    assertEquals(count, lastRecord.getCount());

    lastRecord.setDeleteAt(LocalDateTime.now());
    int deleteCount = recordMapper.delete(lastRecord);
    assertEquals(1, deleteCount);

    Record deletedRecord = recordMapper.queryRecordById(lastRecord.getRecordId());
    assertNotNull(deletedRecord.getDeleteAt());
  }

  @Test
  void testUpdate() {
    long recordId = 114;
    long userId = 12;
    long accountId = 4;
    long recordSortId = 8;
    LocalDateTime localDateTime = LocalDateTime.now();
    String remark = "滴滴打车";
    BigDecimal count = new BigDecimal("11.53");
    LocalDate spendDate = LocalDate.of(2010, 11, 01);
    Record record = new Record();
    record.setRecordId(recordId);
    record.setAccountId(accountId);
    record.setUserId(userId);
    record.setRecordSortId(recordSortId);
    record.setRemark(remark);
    record.setUpdateAt(localDateTime);
    record.setCount(count);
    record.setSpendTime(spendDate);

    int size = recordMapper.update(record);
    assertEquals(1, size);

    Record resultRecord = recordMapper.queryRecordById(recordId);
    assertTrue(DateTimeUtil.LocalDateTimeToMill(record.getUpdateAt()) - DateTimeUtil.LocalDateTimeToMill(resultRecord.getUpdateAt()) < 10);
    assertEquals(spendDate, record.getSpendTime());

    assertEquals(record.getRecordId(), resultRecord.getRecordId());
    assertEquals(record.getAccountId(), resultRecord.getAccountId());
    assertEquals(record.getRemark(), resultRecord.getRemark());
    assertEquals(record.getUserId(), resultRecord.getUserId());
    assertEquals(record.getRecordSortId(), resultRecord.getRecordSortId());
    assertEquals(record.getCount().toString(), resultRecord.getCount().toString());
  }
}
