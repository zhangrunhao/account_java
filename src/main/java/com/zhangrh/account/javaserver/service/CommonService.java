package com.zhangrh.account.javaserver.service;

import java.math.BigDecimal;
import java.util.List;

import com.zhangrh.account.javaserver.service.Bo.TradeBo;

public interface CommonService {

  BigDecimal calculateBalance(List<TradeBo> tradeBos);
}
