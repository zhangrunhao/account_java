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

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO account (name, user_id, cate, icon, create_at) VALUES (#{account.name}, #{account.userId}, #{account.cate}, #{account.icon}, #{account.createAt})")
  int insert(@Param("account") Account account);

  @Select("SELECT * FROM account WHERE id=#{id}")
  @Results({
    @Result(property = "userId", column = "user_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  Account queryId(@Param("id") long id);

  @Select("SELECT * FROM account WHERE user_id=#{user.id}")
  @Results({
    @Result(property = "userId", column = "user_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<Account> queryByUser(@Param("user") User user);

  @Update("UPDATE account SET icon=#{account.icon}, name=#{account.name}, user_id=#{account.userId}, cate=#{account.cate}, update_at=#{account.updateAt} WHERE id=#{account.id};")
  int update(@Param("account") Account account);

  @Update("UPDATE account SET delete_at=#{account.deleteAt} WHERE id=#{account.id}")
  int delete(@Param("account") Account account);
}