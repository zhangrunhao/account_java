package com.zhangrh.account.javaserver.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.service.TradeService;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.resp.TradeDateSortResp;
import com.zhangrh.account.javaserver.web.resp.TradeResp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

  static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

  @Autowired
  TradeService tradeService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<TradeDateSortResp>> doList() {
    List<TradeDateSortResp> resps = new ArrayList<>();
    try {
      UserBo userBo = UserInfoUtil.getUser();
      Map<String, List<TradeBo>> rMap = tradeService.listSortByDate(userBo);
      rMap.forEach((key, value) -> {
        List<TradeResp> rList = new ArrayList<>();
        for (TradeBo tradeBo : value) {
          rList.add(new TradeResp(tradeBo));
        }
        TradeDateSortResp sortResp = new TradeDateSortResp();
        sortResp.setDate(key);
        sortResp.setTrades(rList);
        resps.add(sortResp);
      });
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resps);
  }
}
