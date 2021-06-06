package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {

  @Options(useGeneratedKeys = true, keyProperty = "accountId", keyColumn = "account_book_id")
  @Insert("INSERT INTO account_book (users_id, icon, name, type, color, create_at) VALUES (#{account.usersId}, #{account.icon}, #{account.name}, #{account.color}, #{account.type}, #{account.createAt})")
  void insert(@Param("account") Account account);

  @Select("SELECT * FROM account_book WHERE users_id=#{user.usersId}")
  List<Account> selectList(@Param("user") User user);
}