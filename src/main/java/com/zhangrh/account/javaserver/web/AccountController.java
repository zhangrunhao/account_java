package com.zhangrh.account.javaserver.web;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.dto.AccountAddParam;
import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.service.AccountService;
import com.zhangrh.account.javaserver.service.UserService;
import com.zhangrh.account.javaserver.utils.JwtTokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestHeader(value = "Authorization") String token,
    @Validated @RequestBody AccountAddParam accountAddParam
  ) {
    boolean flag = false;
    try {
      String email = JwtTokenUtil.getJwtValue(token, "email");
      User user = userService.getUserFromEmail(email);
      flag = accountService.add(user.getUsersId(), accountAddParam.getIcon(), accountAddParam.getName(), accountAddParam.getType(), accountAddParam.getColor());
    } catch (Exception e) {
      return CommonResult.success("账户创建成功");
    }
    if (flag) {
      return CommonResult.success("账户创建成功");
    } else {
      return CommonResult.failed("创建失败");
    }
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<Account>> doList(
    @RequestHeader(value = "Authorization") String token
  ) {
    String email = JwtTokenUtil.getJwtValue(token, "email");
    User user = userService.getUserFromEmail(email);
    List<Account> list = accountService.list(user);
    if (list == null) {
      return CommonResult.failed("查询账户失败");
    } else {
      return CommonResult.success(list);
    }
  }
}
