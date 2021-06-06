package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.Account;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

  @Options(useGeneratedKeys = true, keyProperty = "accountId", keyColumn = "account_book_id")
  @Insert("INSERT INTO account_book (users_id, icon, name, type, color, create_at) VALUES (#{account.usersId}, #{account.icon}, #{account.name}, #{account.color}, #{account.type}, #{account.createAt})")
  void insert(@Param("account") Account account);
}