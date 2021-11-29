package com.zhangrh.account.javaserver.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.zhangrh.account.javaserver.service.CommonService;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;

import org.springframework.stereotype.Service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Service
public class CommonServiceImpl implements CommonService {
  // private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

  @Override
  public BigDecimal calculateBalance(List<TradeBo> tradeBos) {
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
