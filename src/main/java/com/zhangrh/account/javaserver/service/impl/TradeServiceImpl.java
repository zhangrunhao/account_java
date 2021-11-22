package com.zhangrh.account.javaserver.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.entity.Trade;
import com.zhangrh.account.javaserver.entity.ViewTradeCateAccount;
import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.TradeCateMapper;
import com.zhangrh.account.javaserver.mapper.TradeMapper;
import com.zhangrh.account.javaserver.mapper.ViewTradeCateAccountMapper;
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
public class TradeServiceImpl implements TradeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TradeServiceImpl.class);

  @Autowired
  TradeMapper tradeMapper;

  @Autowired
  TradeCateMapper tradeCateMapper;

  @Autowired
  ViewTradeCateAccountMapper viewTradeCateAccountMapper;

  @Override
  public void add(TradeBo tradeBo) {
    try {
      tradeBo.setCreateAt(LocalDateTime.now());
      tradeMapper.insert(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录添加失败");
    }
  }

  @Override
  @Transactional
  public void transfer(TradeBo outTradeBo, TradeBo inTradeBo) {
    try {
      outTradeBo.setCreateAt(LocalDateTime.now());
      long outTradeCateId = tradeCateMapper.queryOperate(TradeOperation.Transfer_Out.getCode()).getId();
      outTradeBo.setTradeCateId(outTradeCateId);
      outTradeBo.setOperate(TradeOperation.Transfer_Out);
      tradeMapper.insert(outTradeBo.toTrade());

      inTradeBo.setCreateAt(LocalDateTime.now());
      long inTradeCateId = tradeCateMapper.queryOperate(TradeOperation.Transfer_In.getCode()).getId();
      inTradeBo.setTradeCateId(inTradeCateId);
      inTradeBo.setOperate(TradeOperation.Transfer_In);
      tradeMapper.insert(inTradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("转账交易记录添加失败");
    }
  }

  @Override
  public void update(TradeBo tradeBo) {
    try {
      tradeBo.setUpdateAt(LocalDateTime.now());
      tradeMapper.update(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录更新失败");
    }
  }

  @Override
  public void delete(TradeBo tradeBo) {
    try {
      tradeBo.setDeleteAt(LocalDateTime.now());
      tradeMapper.delete(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录删除失败");
    }
  }

  @Override
  public TradeBo query(TradeBo tradeBo) {
    Trade trade = new Trade();
    try {
      trade = tradeMapper.queryById(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("单条交易记录查询失败");
    }
    return new TradeBo(trade);
  }

  @Override
  public List<TradeBo> list(UserBo userBo) {
    List<TradeBo> tradeBos = new ArrayList<>();
    try {
      List<ViewTradeCateAccount> trades = viewTradeCateAccountMapper.queryByUserId(userBo.getId());
      for (ViewTradeCateAccount trade : trades) {
        if (trade.getDeleteAt() == null) {
          tradeBos.add(new TradeBo(trade));
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录按用户查询失败");
    }
    return tradeBos;
  }

  @Override
  public List<TradeBo> list(AccountBo accountBo) {
    List<TradeBo> tradeBos = new ArrayList<>();
    try {
      List<ViewTradeCateAccount> trades = viewTradeCateAccountMapper.queryByAccountId(accountBo.getId());
      for (ViewTradeCateAccount trade : trades) {
        if (trade.getDeleteAt() == null) {
          tradeBos.add(new TradeBo(trade));
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录按账户查询失败");
    }
    return tradeBos;
  }

  @Override
  public Map<String, List<TradeBo>> listSortByDate(AccountBo accountBo) {
    return sortTradeByDate(list(accountBo));
  }

  @Override
  public Map<String, List<TradeBo>> listSortByDate(UserBo userBo) {
    return sortTradeByDate(list(userBo));
  }

  private Map<String, List<TradeBo>> sortTradeByDate(List<TradeBo> tradeBos) {
    Map<String, List<TradeBo>> result = new HashMap<>();
    for (TradeBo tradeBo : tradeBos) {
      LocalDate spendDate = tradeBo.getSpendDate();
      String dateStr = spendDate.toString();
      List<TradeBo> tradeBoDateList = result.get(dateStr);
      if (tradeBoDateList == null) {
        tradeBoDateList = new ArrayList<>();
        tradeBoDateList.add(tradeBo);
        result.put(dateStr, tradeBoDateList);
      } else {
        tradeBoDateList.add(tradeBo);
      }
    }
    return result;
  }
}
