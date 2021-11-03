package com.zhangrh.account.javaserver.web;
import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.AccountAddRequest;
import com.zhangrh.account.javaserver.request.AccountDeleteRequest;
import com.zhangrh.account.javaserver.request.AccountUpdateRequest;
import com.zhangrh.account.javaserver.response.AccountResponse;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody AccountAddRequest accountAddRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setUserId(user.getUserId());
      account.setIcon(accountAddRequest.getIcon());
      account.setColor(accountAddRequest.getColor());
      account.setName(accountAddRequest.getName());
      account.setType(accountAddRequest.getType());
      accountService.add(user, account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户创建成功");
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<AccountResponse>> doList() {
    User user = UserInfoUtil.getUser();
    List<AccountResponse> accountResponses = new ArrayList<>();
    try {
      List<Account> accounts = accountService.list(user);
      for (Account account: accounts) {
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);
        accountResponses.add(accountResponse);
      }
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(accountResponses);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody AccountUpdateRequest accountUpdateRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setAccountId(accountUpdateRequest.getAccountId());
      account.setColor(accountUpdateRequest.getColor());
      account.setIcon(accountUpdateRequest.getIcon());
      account.setName(accountUpdateRequest.getName());
      account.setType(accountUpdateRequest.getType());
      accountService.update(user, account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户更新成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody AccountDeleteRequest accountDeleteRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setAccountId(accountDeleteRequest.getAccountId());
      accountService.delete(user, account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("账户删除成功");
  }

  @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<AccountResponse> doGetAccount(
    @RequestParam long accountId
  ) {
    User user = UserInfoUtil.getUser();
    Account account = null;
    AccountResponse accountResponse = null;
    try {
      account = accountService.getAccountByAccountId(user, accountId);
      // TODO: 如何在此处计算当前余额
      accountResponse = new AccountResponse();
      BeanUtils.copyProperties(account, accountResponse);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(accountResponse);
  }
}
