package com.zhangrh.account.javaserver.service;

import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

public interface TradeService {

  void add(TradeBo tradeBo);

  void transfer(TradeBo outTradeBo, TradeBo inTradeBo);

  void addBorrowLend(TradeBo tradeBo);

  void addRepaymentReceive(TradeBo tradeBo, long targetTradeId);

  void updateBorrowLend(TradeBo tradeBo);

  void update(TradeBo tradeBo);

  void delete(TradeBo tradeBo);

  TradeBo query(TradeBo tradeBo);

  List<TradeBo> list(UserBo userBo);

  List<TradeBo> list(AccountBo accountBo);

  List<TradeBo> list(TradeOperation tradeOperation);

  Map<String, List<TradeBo>> listByBorrowLendId(long borrowLendId);

  Map<String, List<TradeBo>> listSortByDate(UserBo userBo);

  Map<String, List<TradeBo>> listSortByDate(AccountBo accountBo);

  Map<String, List<TradeBo>> listSortByDate(TradeOperation tradeOperation);
}
