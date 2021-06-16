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
import com.zhangrh.account.javaserver.service.UserService;
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

  @Autowired
  UserService userService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody AccountAddRequest accountAddRequest
  ) {
    boolean flag = false;
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setUsersId(user.getUserId());
      account.setIcon(accountAddRequest.getIcon());
      account.setColor(accountAddRequest.getColor());
      account.setName(accountAddRequest.getName());
      account.setType(accountAddRequest.getType());
      flag = accountService.add(user, account);
    } catch (Exception e) {
      return CommonResult.failed("账户创建失败");
    }
    if (flag) {
      return CommonResult.success("账户创建成功");
    } else {
      return CommonResult.failed("创建失败");
    }
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<AccountResponse>> doList() {
    User user = UserInfoUtil.getUser();
    List<Account> accounts = accountService.list(user);
    List<AccountResponse> accountResponses = new ArrayList<AccountResponse>();
    for (Account account: accounts) {
      AccountResponse accountResponse = new AccountResponse();
      BeanUtils.copyProperties(account, accountResponse);
      accountResponses.add(accountResponse);
    }
    return CommonResult.success(accountResponses);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody AccountUpdateRequest accountUpdateRequest
  ) {
    boolean flag;
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setAccountId(accountUpdateRequest.getAccountId());
      account.setColor(accountUpdateRequest.getColor());
      account.setIcon(accountUpdateRequest.getIcon());
      account.setName(accountUpdateRequest.getName());
      account.setType(accountUpdateRequest.getType());
      flag = accountService.update(user, account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    if (flag) return CommonResult.success("账户更新成功");
    return CommonResult.failed("账户更新失败");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody AccountDeleteRequest accountDeleteRequest
  ) {
    boolean flag;
    try {
      User user = UserInfoUtil.getUser();
      Account account = new Account();
      account.setAccountId(accountDeleteRequest.getAccountId());
      flag = accountService.delete(user, account);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    if (flag) return CommonResult.success("账户删除成功");
    return CommonResult.failed("账户删除失败");
  }

  @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<AccountResponse> doGetAccount(
    @RequestParam String accountId
  ) {
    User user = UserInfoUtil.getUser();
    Account account = accountService.getAccountByAccountId(user, accountId);
    if (account != null) {
      AccountResponse accountResponse = new AccountResponse();
      BeanUtils.copyProperties(account, accountResponse);
      return CommonResult.success(accountResponse);
    } else {
      return CommonResult.failed();
    }
  }
}
