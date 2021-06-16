package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {

  @Options(useGeneratedKeys = true, keyProperty = "accountId", keyColumn = "account_book_id")
  @Insert("INSERT INTO account_book (users_id, icon, name, type, color, create_at) VALUES (#{account.userId}, #{account.icon}, #{account.name}, #{account.type}, #{account.color}, #{account.createAt})")
  void insert(@Param("account") Account account);

  @Select("SELECT * FROM account_book WHERE users_id=#{user.userId} AND delete_at IS NULL")
  @Results({
    @Result(property = "accountId", column = "account_book_id")
  })
  List<Account> selectList(@Param("user") User user);

  @Select("SELECT * FROM account_book WHERE account_book_id=#{accountId} AND users_id=#{user.userId}")
  Account getAccountByAccountId(@Param("accountId") String accountId, @Param("user") User user);

  @Update("UPDATE account_book SET icon=#{account.icon},name=#{account.name},type=#{account.type},color=#{account.color},update_at=#{account.updateAt} WHERE account_book_id=#{account.accountId} AND users_id=#{user.userId} AND delete_at IS NULL")
  int update(@Param("account") Account account, @Param("user") User user);

  @Update("UPDATE account_book SET delete_at=#{account.deleteAt} WHERE account_book_id=#{account.accountId} AND users_id=#{user.userId} AND delete_at IS NULL")
  int delete(@Param("account") Account account, @Param("user") User user);
}