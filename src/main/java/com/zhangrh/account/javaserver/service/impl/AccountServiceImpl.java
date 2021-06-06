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
  public boolean add(long user_id, String icon, String name, String type, String color) {
    try {
      Account account = new Account();
      account.setUsersId(user_id);
      account.setIcon(icon);
      account.setName(name);
      account.setType(type);
      account.setColor(color);
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
  public boolean update(long account_id, String icon, String name, String type, String color) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delete(User user) {
    // TODO Auto-generated method stub
    return false;
  }
  
}
