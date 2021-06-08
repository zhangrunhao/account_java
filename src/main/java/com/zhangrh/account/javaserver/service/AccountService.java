package com.zhangrh.account.javaserver.service;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;

public interface AccountService {
  /**
   * 添加账户
   * @param user_id 用户id
   * @param icon 图标
   * @param name 账户名称
   * @param type 账户类型 0 普通账户 / 1 负债账户
   * @param color 颜色
   * @return 是否添加成功
   */
  boolean add(long user_id, String icon, String name, String type, String color);

  /**
   * 查询账户列表
   * @param user_id 用户id
   * @return 用户列表
   */
  List<Account> list(User user);

  /**
   *  账户更新
   * @param account 账户
   * @param user 用户
   * @return 是否更新成功
   */
  boolean update(Account account, User user);

  /**
   * 删除账户
   * @param account_id 账户id
   * @return 是否删除成功
   */
  boolean delete(User user);
}
