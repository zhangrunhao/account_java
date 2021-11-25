package com.zhangrh.account.javaserver.service;

import java.math.BigDecimal;
import java.util.List;

import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

public interface AccountService {
  /**
   * 添加账户
   * @param user 用户
   * @param accountBo 账户
   */
  void add(AccountBo accountBo);

  /**
   * 添加默认账户
   * @param userBo 用户
   */
  void addDefault(UserBo userBo);

  /**
   * 查询账户列表
   * @param User 用户
   * @return 用户列表
   */
  List<AccountBo> list(UserBo userBo);

  /**
   *  账户更新
   * @param accountBo 账户
   * @return 是否更新成功
   */
  void update(AccountBo accountBo);

  /**
   * 删除账户
   * @param accountBo 账户
   * @return 是否删除成功
   */
  void delete(AccountBo accountBo);

  /**
   * 根据账户id获取账户信息
   * @param userId 用户
   * @param accountId 账户id
   * @return 账户信息
   */
  AccountBo get(AccountBo accountBo);


  /**
   * 计算账户余额
   * @param account 账户
   * @return 账户余额
   */
  BigDecimal calculateBalance(AccountBo accountBo);
}
