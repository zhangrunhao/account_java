package com.zhangrh.account.javaserver.service;

import java.util.List;

import com.zhangrh.account.javaserver.service.Bo.TradeCateBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

public interface TradeCateService {

  void add(TradeCateBo tradeCateBo);

  void addDefault(UserBo userBo);

  List<TradeCateBo> list(UserBo userBo);

  void update(TradeCateBo tradeCateBo);

  void delete(TradeCateBo tradeCateBo);

  TradeCateBo get(TradeCateBo tradeCateBo);
}
