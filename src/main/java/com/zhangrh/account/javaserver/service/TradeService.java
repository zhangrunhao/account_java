package com.zhangrh.account.javaserver.service;

import java.util.List;

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

  List<TradeBo> listSortByDat(UserBo userBo);

  List<TradeBo> listSortByDate(AccountBo accountBo);
}
