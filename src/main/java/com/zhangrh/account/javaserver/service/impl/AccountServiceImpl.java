package com.zhangrh.account.javaserver.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.AccountMapper;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Autowired
  AccountMapper accountMapper;

  @Override
  @Transactional
  public void add(AccountBo accountBo) {
    try {
      accountBo.setCreateAt(LocalDateTime.now());
      Account account = AccountBo.toAccountEntity(accountBo);
      accountMapper.insert(account);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户创建账户失败");
    }
  }

  @Override
  public List<AccountBo> list(UserBo userBo) {
    List<AccountBo> accountBos = new ArrayList<>();
    try {
      List<Account> accounts = accountMapper.queryByUser(userBo.toUser());
      for (Account account : accounts) {
        accountBos.add(new AccountBo(account));
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询账户列表失败");
    }
    return accountBos;
  }

  @Override
  public void update(AccountBo accountBo) {
  }

  @Override
  public void delete(AccountBo accountBo) {
  }



  @Override
  public AccountBo get(AccountBo accountBo) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public BigDecimal calculateBalance(AccountBo accountBo) {
    // TODO Auto-generated method stub
    return null;
  }
}
