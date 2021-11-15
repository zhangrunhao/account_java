package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.Account;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO account (name, type, cate, icon, create_at) VALUES (#{account.name}, #{account.type}, #{account.cate}, #{account.icon}, #{account.createAt})")
  int insert(@Param("account") Account account);

  @Select("SELECT * FROM account WHERE id=#{id}")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  Account queryId(@Param("id") long id);

  @Update("UPDATE account SET icon=#{account.icon}, name=#{account.name}, type=#{account.type}, cate=#{account.cate}, update_at=#{account.updateAt} WHERE id=#{account.id};")
  int update(@Param("account") Account account);

  @Update("UPDATE account SET delete_at=#{account.deleteAt} WHERE id=#{account.id}")
  int delete(@Param("account") Account account);
}