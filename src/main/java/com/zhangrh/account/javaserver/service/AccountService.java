package com.zhangrh.account.javaserver.service;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;

public interface AccountService {
  /**
   * 添加账户
   * @param user 用户
   * @param account 账户
   * @return 是否添加成功
   */
  void add(User user, Account account);

  /**
   * 查询账户列表
   * @param user_id 用户id
   * @return 用户列表
   */
  List<Account> list(User user);

  /**
   *  账户更新
   * @param user 用户
   * @param account 账户
   * @return 是否更新成功
   */
  void update(User user, Account account);

  /**
   * 删除账户
   * @param user 用户
   * @param account 账户
   * @return 是否删除成功
   */
  void delete(User user, Account account);

  /**
   * 根据账户id获取账户信息
   * @param user 用户
   * @param accountId 账户id
   * @return 账户信息
   */
  Account getAccountByAccountId(User user, long accountId);
}
