package com.zhangrh.account.javaserver.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.service.TradeService;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.DateTimeUtil;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.req.TradeAddReq;
import com.zhangrh.account.javaserver.web.req.TradeDeleteReq;
import com.zhangrh.account.javaserver.web.req.TradeTransferReq;
import com.zhangrh.account.javaserver.web.req.TradeUpdateReq;
import com.zhangrh.account.javaserver.web.resp.TradeDateSortResp;
import com.zhangrh.account.javaserver.web.resp.TradeResp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

  static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

  @Autowired
  TradeService tradeService;

  @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<TradeResp> doGetDetail(@RequestParam long id) {
    TradeResp resp = null;
    try {
      TradeBo tradeBo = new TradeBo();
      tradeBo.setTradeId(id);
      tradeBo = tradeService.query(tradeBo);
      resp = new TradeResp(tradeBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resp);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(@Validated @RequestBody TradeAddReq req) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      TradeBo tradeBo = new TradeBo();
      tradeBo.setUserId(userBo.getId());
      tradeBo.setAccountId(req.getAccountId());
      tradeBo.setMoney(new BigDecimal(req.getMoney()));
      tradeBo.setOperate(TradeOperation.getByCode(req.getOperate()));
      tradeBo.setTradeCateId(req.getTradeCateId());
      tradeBo.setRemark(req.getRemark());
      tradeBo.setSpendDate(DateTimeUtil.MillToLocalDate(req.getSpendDate()));
      tradeService.add(tradeBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("添加成功");
  }

  @RequestMapping(value = "/addTransfer", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doTransfer(@Validated @RequestBody TradeTransferReq req) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      // out trade
      TradeBo outTradeBo = new TradeBo();
      outTradeBo.setUserId(userBo.getId());
      outTradeBo.setAccountId(req.getOutAccountId());
      outTradeBo.setMoney(new BigDecimal(req.getMoney()));
      outTradeBo.setSpendDate(DateTimeUtil.MillToLocalDate(req.getSpendDate()));
      // in trade
      TradeBo inTradeBo = new TradeBo();
      inTradeBo.setUserId(userBo.getId());
      inTradeBo.setAccountId(req.getInAccountId());
      inTradeBo.setMoney(new BigDecimal(req.getMoney()));
      inTradeBo.setSpendDate(DateTimeUtil.MillToLocalDate(req.getSpendDate()));
      tradeService.transfer(outTradeBo, inTradeBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("添加成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(@Validated @RequestBody TradeDeleteReq req) {
    try {
      TradeBo tradeBo = new TradeBo();
      tradeBo.setTradeId(req.getId());
      tradeService.delete(tradeBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("添加成功");
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(@Validated @RequestBody TradeUpdateReq req) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      TradeBo tradeBo = new TradeBo();
      tradeBo.setUserId(userBo.getId());
      tradeBo.setTradeId(req.getId());
      tradeBo.setAccountId(req.getAccountId());
      tradeBo.setMoney(new BigDecimal(req.getMoney()));
      tradeBo.setOperate(TradeOperation.getByCode(req.getOperate()));
      tradeBo.setTradeCateId(req.getTradeCateId());
      tradeBo.setRemark(req.getRemark());
      tradeBo.setSpendDate(DateTimeUtil.MillToLocalDate(req.getSpendDate()));
      tradeService.update(tradeBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("更新成功");
  }

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
      Collections.sort(resps);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resps);
  }

  @RequestMapping(value = "/listByAccount", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<TradeDateSortResp>> doListByAccount(@RequestParam long id) {
    AccountBo accountBo = new AccountBo();
    accountBo.setId(id);
    List<TradeDateSortResp> resps = new ArrayList<>();
    try {
      Map<String, List<TradeBo>> rMap = tradeService.listSortByDate(accountBo);
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
      Collections.sort(resps);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resps);
  }
}
