package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    Date spendTime = new Date();
    Record record = new Record();
    record.setUserId(userId);
    record.setAccountId(accountId);
    record.setRecordSortId(recordSortId);
    record.setRemark(remark);
    record.setSpendTime(spendTime);
    recordService.add(record);

    // query list by user
    User user = new User();
    user.setUserId(userId);
    List<Record> userList = recordService.getListByUser(user);
    Record userListLastRecord = userList.get(userList.size() - 1);
    assertTrue(spendTime.getTime() <= userListLastRecord.getCreateAt().getTime());
    assertEquals(userId, userListLastRecord.getUserId());
    assertEquals(accountId, userListLastRecord.getAccountId());
    assertEquals(recordSortId, userListLastRecord.getRecordSortId());
    assertEquals(spendTime.getTime(), userListLastRecord.getSpendTime().getTime());

    // update
    Record updateRecord = new Record();
    String updateRemark = "忘记了";
    Date updateSpendTime = new Date(1000000);
    updateRecord.setUserId(userId);
    updateRecord.setAccountId(accountId);
    updateRecord.setRecordSortId(recordSortId);
    updateRecord.setRemark(updateRemark);
    updateRecord.setSpendTime(updateSpendTime);
    updateRecord.setRecordId(userListLastRecord.getRecordId());
    recordService.update(updateRecord);

    // query list by account
    Account account = new Account();
    account.setAccountId(accountId);
    account.setUserId(userId);
    List<Record> accountList = recordService.getListByAccount(account);
    Record accountListLastRecord = accountList.get(accountList.size() - 1);
    assertTrue(updateSpendTime. getTime() <= accountListLastRecord.getUpdateAt().getTime());
    assertEquals(userId, accountListLastRecord.getUserId());
    assertEquals(accountId, accountListLastRecord.getAccountId());
    assertEquals(recordSortId, accountListLastRecord.getRecordSortId());
    assertEquals(updateSpendTime.getTime(), accountListLastRecord.getSpendTime().getTime());

    // delete and get record by id
    recordService.delete(accountListLastRecord);
    Record deletedRecord = recordService.getRecordById(accountListLastRecord.getRecordId());
    assertNull(deletedRecord);

    // query list by between
    List<Record> betweenList = recordService.getListByAccountAndTime(account, new Date(300), new Date());
    assertTrue(betweenList.size() > 0);
  }
}
