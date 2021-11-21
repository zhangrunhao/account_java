package com.zhangrh.account.javaserver.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.AccountMapper;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.TradeService;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
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

  @Autowired
  TradeService tradeService;

  @Override
  @Transactional
  public void add(AccountBo accountBo) {
    try {
      accountBo.setCreateAt(LocalDateTime.now());
      Account account = accountBo.toAccountEntity();
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
        if (account.getDeleteAt() == null) { // 排除已经删除的账户
          AccountBo accountBo = new AccountBo(account);
          accountBo.setMoney(calculateBalance(accountBo));
          accountBos.add(accountBo);
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询账户列表失败");
    }
    return accountBos;
  }

  @Override
  public AccountBo get(AccountBo accountBo) {
    Account account = null;
    try {
      account = accountMapper.queryId(accountBo.getId());
      accountBo = new AccountBo(account);
      accountBo.setMoney(calculateBalance(accountBo));
      if (account.getDeleteAt() != null)
        throw new Error("account is deleted");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询账户信息失败");
    }
    return accountBo;
  }

  @Override
  public void update(AccountBo accountBo) {
    try {
      accountBo.setUpdateAt(LocalDateTime.now());
      accountMapper.update(accountBo.toAccountEntity());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("账户更新失败");
    }
  }

  @Override
  public void delete(AccountBo accountBo) {
    try {
      accountBo.setDeleteAt(LocalDateTime.now());
      accountMapper.delete(accountBo.toAccountEntity());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("账户更新失败");
    }
  }

  @Override
  public BigDecimal calculateBalance(AccountBo accountBo) {
    List<TradeBo> tradeBos = tradeService.list(accountBo);
    BigDecimal result = new BigDecimal(0);
    for (TradeBo tradeBo : tradeBos) {
      BigDecimal money = tradeBo.getMoney();
      switch (tradeBo.getOperate().getSign()) {
      case "subtract":
        result = result.subtract(money);
        break;
      case "add":
        result = result.add(money);
        break;
      }
    }
    return result;
  }
}
