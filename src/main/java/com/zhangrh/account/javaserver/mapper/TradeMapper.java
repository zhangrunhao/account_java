package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Trade;
import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;


public interface TradeMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO trade (user_id, account_id, trade_cate_id, money, remark, spend_date, operate, create_at) VALUES (#{p.userId}, #{p.accountId}, #{p.tradeCateId}, #{p.money}, #{p.remark}, #{p.spendDate}, #{p.operate}, #{p.createAt})")
  int insert(@Param("p") Trade trade);

  @Update("UPDATE trade SET user_id=#{p.userId}, account_id=#{p.accountId}, trade_cate_id=#{p.tradeCateId}, money=#{p.money}, remark=#{p.remark}, spend_date=#{p.spendDate}, operate=#{p.operate}, update_at=#{p.updateAt} WHERE id=#{p.id};")
  int update(@Param("p") Trade trade);


  @Select("SELECT * FROM trade WHERE id=#{t.id}")
  @Results({
      @Result(property = "userId", column = "users_id"),
      @Result(property = "accountId", column = "account_id"),
      @Result(property = "tradeCateId", column = "trace_cate_id"),
      @Result(property = "spendDate", column = "spend_date"),
      @Result(property = "createAt", column = "create_at"),
      @Result(property = "updateAt", column = "update_at"),
      @Result(property = "deleteAt", column = "delete_at"),
      @Result(property = "accountName", column = "account_name"),
      @Result(property = "sortName", column = "sort_name")
  })
  Trade queryById(@Param("t") Trade trade);

  @Select("SELECT * FROM trade WHERE account_id=#{a.id}")
  @Results({
      @Result(property = "userId", column = "users_id"),
      @Result(property = "accountId", column = "account_id"),
      @Result(property = "tradeCateId", column = "trace_cate_id"),
      @Result(property = "spendDate", column = "spend_date"),
      @Result(property = "createAt", column = "create_at"),
      @Result(property = "updateAt", column = "update_at"),
      @Result(property = "deleteAt", column = "delete_at"),
      @Result(property = "accountName", column = "account_name"),
      @Result(property = "sortName", column = "sort_name")
  })
  List<Trade> queryByAccount(@Param("a") Account account);

  @Select("SELECT * FROM trade WHERE user_id=#{u.id}")
  @Results({
      @Result(property = "userId", column = "users_id"),
      @Result(property = "accountId", column = "account_id"),
      @Result(property = "tradeCateId", column = "trace_cate_id"),
      @Result(property = "spendDate", column = "spend_date"),
      @Result(property = "createAt", column = "create_at"),
      @Result(property = "updateAt", column = "update_at"),
      @Result(property = "deleteAt", column = "delete_at"),
      @Result(property = "accountName", column = "account_name"),
      @Result(property = "sortName", column = "sort_name")
  })
  List<Trade> queryByUser(@Param("u") User user);

  @Update("UPDATE trade SET delete_at=#{p.deleteAt} WHERE id=#{p.id};")
  int delete(@Param("p") Trade trade);
}
