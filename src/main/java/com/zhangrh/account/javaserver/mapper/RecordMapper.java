package com.zhangrh.account.javaserver.mapper;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RecordMapper {
  
  @Options(useGeneratedKeys = true, keyProperty = "recordId", keyColumn = "income_expend_record_id")
  @Insert("INSERT INTO income_expend_record (users_id, income_expend_record_sort_id, account_book_id, remark, spend_time, create_at) VALUES (#{record.userId}, #{record.recordSortId}, #{record.accountId}, #{record.remark}, #{record.spendTime}, #{record.createAt})")
  void insert(@Param("record") Record record);

  @Update("UPDATE income_expend_record SET users_id=#{record.userId}, income_expend_record_sort_id=#{record.recordSortId}, account_book_id=#{record.accountId}, remark=#{record.remark}, spend_time=#{record.spendTime}, update_at=#{record.updateAt} WHERE income_expend_record_id=#{record.recordId}")
  int update(@Param("record") Record record);

  @Update("UPDATE income_expend_record SET delete_at=#{record.deleteAt} WHERE income_expend_record_id=#{record.recordId}")
  int delete(@Param("record") Record record);

  @Select("SELECT * FROM income_expend_record WHERE income_expend_record_id=#{recordId}")
  @Results({
    @Result(property = "userId", column = "users_id"),
    @Result(property = "spendTime", column = "spend_time"),
    @Result(property = "accountId", column = "account_book_id"),
    @Result(property = "recordId", column = "income_expend_record_id"),
    @Result(property = "recordSortId", column = "income_expend_record_sort_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  Record queryRecordById(@Param("recordId") long recordId);

  @Select("SELECT * FROM income_expend_record WHERE users_id=#{user.userId}")
  @Results({
    @Result(property = "userId", column = "users_id"),
    @Result(property = "accountId", column = "account_book_id"),
    @Result(property = "spendTime", column = "spend_time"),
    @Result(property = "recordId", column = "income_expend_record_id"),
    @Result(property = "recordSortId", column = "income_expend_record_sort_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<Record> queryListByUser(@Param("user") User user);

  @Select("SELECT * FROM income_expend_record WHERE users_id=#{account.userId} AND account_book_id=#{account.accountId}")
  @Results({
    @Result(property = "userId", column = "users_id"),
    @Result(property = "spendTime", column = "spend_time"),
    @Result(property = "accountId", column = "account_book_id"),
    @Result(property = "recordId", column = "income_expend_record_id"),
    @Result(property = "recordSortId", column = "income_expend_record_sort_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<Record> queryListByAccount(@Param("account") Account account);

  @Select(
  "SELECT * " +
  "FROM income_expend_record " +
  "WHERE users_id=1 " +
  "AND account_book_id=3 " +
  "AND spend_time > #{fromDate} " +
  "AND spend_time < #{toDate}")
  @Results({
    @Result(property = "userId", column = "users_id"),
    @Result(property = "accountId", column = "account_book_id"),
    @Result(property = "recordId", column = "income_expend_record_id"),
    @Result(property = "recordSortId", column = "income_expend_record_sort_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<Record> queryListByAccountAndTime(
    @Param("account") Account account,
    @Param("fromDate") Date fromDate,
    @Param("toDate") Date toDate
  );

}
