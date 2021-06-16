package com.zhangrh.account.javaserver.service.impl;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.mapper.AccountMapper;
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

  @Override
  public boolean add(User user, Account account) {
    try {
      account.setCreateAt(new Date().getTime());
      accountMapper.insert(account);
    } catch (Exception e) {
      LOGGER.warn("用户创建账户失败", e);
      return false;
    }
    return true;
  }

  @Override
  public List<Account> list(User user) {
    List<Account> list = null;
    try {
      list = accountMapper.selectList(user);
    } catch (Exception e) {
      LOGGER.warn("查询账户列表出错: " + e.getMessage());
      return null;
    }
    return list;
  }

  @Override
  public boolean update(User user, Account account) {
    boolean flag = false;
    try {
      account.setUpdateAt(new Date().getTime());
      int size = accountMapper.update(account, user);
      if (size == 1) flag = true;
    } catch (Exception e) {
      flag = false;
      LOGGER.warn("账户更新失败: " + e.getMessage());
    }
    return flag;
  }

  @Override
  public boolean delete(User user, Account account) {
    boolean flag = false;
    try {
      account.setDeleteAt(new Date().getTime());
      int size = accountMapper.delete(account, user);
      if (size == 1) flag = true;
    } catch (Exception e) {
      flag = false;
      LOGGER.warn("账户删除失败: " + e.getMessage());
    }
    return flag;
  }

  @Override
  public Account getAccountByAccountId(User user, String accountId) {
    Account account = null;
    try {
      account = accountMapper.getAccountByAccountId(accountId, user);
    } catch (Exception e) {
      LOGGER.warn("账户信息查询失败: " + e.getMessage());
    }
    return account;
  }
  
}
