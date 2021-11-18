package com.zhangrh.account.javaserver.web;

import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.service.TradeCateService;
import com.zhangrh.account.javaserver.service.Bo.TradeCateBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.req.TradeCateAddReq;
import com.zhangrh.account.javaserver.web.req.TradeCateDeleteReq;
import com.zhangrh.account.javaserver.web.req.TradeCateUpdateReq;
import com.zhangrh.account.javaserver.web.resp.TradeCateResp;

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
@RequestMapping("/api/trade_cate")
public class TradeCateController {
  static final Logger LOGGER = LoggerFactory.getLogger(TradeCateController.class);

  @Autowired
  TradeCateService tradeCateService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody TradeCateAddReq req
  ) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      TradeCateBo tradeCateBo = new TradeCateBo();
      tradeCateBo.setUserId(userBo.getId());
      tradeCateBo.setName(req.getName());
      tradeCateBo.setIcon(req.getIcon());
      tradeCateBo.setOperate(TradeOperation.getByCode(req.getOperate()));
      tradeCateService.add(tradeCateBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支记录种类创建成功");
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<TradeCateResp>> doList() {
    UserBo userBo = UserInfoUtil.getUser();
    List<TradeCateResp> resps = new ArrayList<>();
    try {
      List<TradeCateBo> bos = tradeCateService.list(userBo);
      for (TradeCateBo tradeCateBo : bos) {
        resps.add(new TradeCateResp(tradeCateBo));
      }
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resps);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody TradeCateUpdateReq req
  ) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      TradeCateBo tradeCateBo = new TradeCateBo();
      tradeCateBo.setUserId(userBo.getId());
      tradeCateBo.setTradeCateId(req.getId());
      tradeCateBo.setIcon(req.getIcon());
      tradeCateBo.setName(req.getName());
      tradeCateBo.setOperate(TradeOperation.getByCode(req.getOperate()));
      tradeCateService.update(tradeCateBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("用户收支种类更新成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody TradeCateDeleteReq req
  ) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      TradeCateBo bo = new TradeCateBo();
      bo.setUserId(userBo.getId());
      bo.setTradeCateId(req.getId());
      tradeCateService.delete(bo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支类型删除成功");
  }


  @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<TradeCateResp> doGetRecordSort(
    @RequestParam long id
  ) {
    TradeCateBo tradeCateBo = new TradeCateBo();
    try {
      tradeCateBo.setTradeCateId(id);
      tradeCateBo = tradeCateService.get(tradeCateBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(new TradeCateResp(tradeCateBo));
  }
}
