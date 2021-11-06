package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.utils.DateTimeUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordServiceTest {
  
  @Autowired
  RecordService recordService;

  @Test
  void test() {
    // insert
    long userId = 1;
    long accountId = 3;
    long recordSortId = 2;
    String remark = "滴滴";
    LocalDate spendTime = LocalDate.now();
    Record record = new Record();
    record.setUserId(userId);
    record.setAccountId(accountId);
    record.setRecordSortId(recordSortId);
    record.setRemark(remark);
    record.setSpendTime(spendTime);
    record.setCount(new BigDecimal(10));
    recordService.add(record);

    // query list by user
    User user = new User();
    user.setUserId(userId);
    List<Record> userList = recordService.getListByUser(user);
    Record userListLastRecord = userList.get(userList.size() - 1);

    assertTrue(DateTimeUtil.LocalDateToMill(spendTime) <= DateTimeUtil.LocalDateTimeToMill(userListLastRecord.getCreateAt()));

    assertEquals(userId, userListLastRecord.getUserId());
    assertEquals(accountId, userListLastRecord.getAccountId());
    assertEquals(recordSortId, userListLastRecord.getRecordSortId());

    assertEquals(DateTimeUtil.LocalDateToMill(spendTime), DateTimeUtil.LocalDateToMill(userListLastRecord.getSpendTime()));

    // update
    Record updateRecord = new Record();
    String updateRemark = "忘记了";
    LocalDate  updateSpendTime = LocalDate.of(2020, 01, 12);
    updateRecord.setUserId(userId);
    updateRecord.setAccountId(accountId);
    updateRecord.setRecordSortId(recordSortId);
    updateRecord.setRemark(updateRemark);
    updateRecord.setSpendTime(updateSpendTime);
    updateRecord.setRecordId(userListLastRecord.getRecordId());
    record.setCount(new BigDecimal(20));
    recordService.update(updateRecord);

    // query list by account
    Account account = new Account();
    account.setAccountId(accountId);
    account.setUserId(userId);
    List<Record> accountList = recordService.getListByAccount(account);
    Record accountListLastRecord = accountList.get(accountList.size() - 1);
    assertTrue(DateTimeUtil.LocalDateToMill(updateSpendTime) <= DateTimeUtil.LocalDateTimeToMill(accountListLastRecord.getUpdateAt()));

    assertEquals(userId, accountListLastRecord.getUserId());
    assertEquals(accountId, accountListLastRecord.getAccountId());
    assertEquals(recordSortId, accountListLastRecord.getRecordSortId());
    assertEquals(updateSpendTime, accountListLastRecord.getSpendTime());

    // delete and get record by id
    recordService.delete(accountListLastRecord);
    Record deletedRecord = recordService.getRecordById(accountListLastRecord.getRecordId());
    assertNull(deletedRecord);
  }

  @Test
  void testGetDateGroupRecordByUser() {
    User user = new User();
    user.setUserId(1);
    recordService.getDateGroupRecordByUser(user);
  }
}
