package com.zhangrh.account.javaserver.web;
import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.enums.AccountCate;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;
import com.zhangrh.account.javaserver.web.req.AccountAddReq;
import com.zhangrh.account.javaserver.web.req.AccountDeleteReq;
import com.zhangrh.account.javaserver.web.req.AccountUpdateReq;
import com.zhangrh.account.javaserver.web.resp.AccountResp;

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
@RequestMapping("/api/account")
public class AccountController {
  static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  AccountService accountService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<AccountResp>> doList() {
    UserBo userBo = UserInfoUtil.getUser();
    List<AccountResp> accountResps = new ArrayList<>();
    try {
      List<AccountBo> accountBos = accountService.list(userBo);
      for (AccountBo accountBo: accountBos) {
        accountResps.add(new AccountResp(accountBo));
      }
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(accountResps);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody AccountAddReq accountAddReq
  ) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      AccountBo accountBo = new AccountBo();
      accountBo.setUserId(userBo.getId());
      accountBo.setName(accountAddReq.getName());
      accountBo.setIcon(accountAddReq.getIcon());
      accountBo.setCate(AccountCate.getByCode(accountAddReq.getCate()));
      accountService.add(accountBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户创建成功");
  }



  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody AccountUpdateReq req
  ) {
    try {
      UserBo userBo = UserInfoUtil.getUser();
      AccountBo account = new AccountBo();
      account.setUserId(userBo.getId());
      account.setId(req.getId());
      account.setName(req.getName());
      account.setCate(AccountCate.getByCode(req.getCate()));
      account.setIcon(req.getIcon());
      accountService.update(account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户更新成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody AccountDeleteReq req
  ) {
    try {
      AccountBo accountBo = new AccountBo();
      accountBo.setId(req.getId());
      accountService.delete(accountBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户删除成功");
  }

  @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<AccountResp> doGetAccount(
    @RequestParam long id
  ) {
    AccountResp resp = null;
    try {
      AccountBo accountBo = new AccountBo();
      accountBo.setId(id);
      accountBo = accountService.get(accountBo);
      resp = new AccountResp(accountBo);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(resp);
  }
}
