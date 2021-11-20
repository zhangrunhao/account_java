package com.zhangrh.account.javaserver.service;

import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

public interface TradeService {

  void add(TradeBo tradeBo);

  void update(TradeBo tradeBo);

  void delete(TradeBo tradeBo);

  TradeBo query(TradeBo tradeBo);

  List<TradeBo> list(UserBo userBo);

  List<TradeBo> list(AccountBo accountBo);

  Map<String, List<TradeBo>> listSortByDate(UserBo userBo);

  Map<String, List<TradeBo>> listSortByDate(AccountBo accountBo);
}
