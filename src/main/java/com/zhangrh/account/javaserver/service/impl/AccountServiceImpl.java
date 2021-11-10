package com.zhangrh.account.javaserver.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.AccountMapper;
import com.zhangrh.account.javaserver.mapper.RecordMapper;
import com.zhangrh.account.javaserver.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  RecordMapper recordMapper;

  @Override
  public BigDecimal calculateAccountBalance(Account account) {
    List<Record> records = recordMapper.queryListByAccount(account);
    BigDecimal result = new BigDecimal(0);
    for (Record record : records) {
      result = result.add(record.getCount());
    }
    return result;
  }

  @Override
  public void add(User user, Account account) {
    try {
      account.setCreateAt(LocalDateTime.now());
      accountMapper.insert(account);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户创建账户失败");
    }
  }

  @Override
  public List<Account> list(User user) {
    List<Account> list = null;
    try {
      list = accountMapper.selectList(user);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询账户列表失败");
    }
    return list;
  }

  @Override
  public void update(User user, Account account) {
    try {
      account.setUpdateAt(LocalDateTime.now());
      int size = accountMapper.update(account, user);
      if (size != 1) throw new Exception("update row size is not 1");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("账户更新失败");
    }
  }

  @Override
  public void delete(User user, Account account) {
    try {
      account.setDeleteAt(LocalDateTime.now());
      int size = accountMapper.delete(account, user);
      if (size != 1) throw new Exception("delete row size is not 1");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("账户删除失败");
    }
  }

  @Override
  public Account getAccountByAccountId(User user, long accountId) {
    Account account = null;
    try {
      account = accountMapper.getAccountByAccountId(accountId, user);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("账户信息查询失败");
    }
    return account;
  }
}
