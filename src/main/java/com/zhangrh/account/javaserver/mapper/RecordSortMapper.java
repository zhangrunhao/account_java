package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.RecordSort;
import com.zhangrh.account.javaserver.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RecordSortMapper {
  
  @Options(useGeneratedKeys = true, keyProperty = "recordSortId", keyColumn = "income_expend_record_sort_id")
  @Insert("INSERT INTO income_expend_record_sort (users_id, icon, name, type, create_at) VALUES (#{recordSort.userId}, #{recordSort.icon}, #{recordSort.name}, #{recordSort.type}, #{recordSort.createAt})")
  void insert(@Param("recordSort") RecordSort recordSort);
  

  @Select("SELECT * FROM income_expend_record_sort WHERE users_id=#{user.userId} AND delete_at IS NULL")
  @Results({
    @Result(property = "recordSortId", column = "income_expend_record_sort_id")
  })
  List<RecordSort> selectList(@Param("user") User user);

  @Select("SELECT * FROM income_expend_record_sort WHERE income_expend_record_sort_id=#{recordSortId} AND users_id=#{user.userId} AND delete_at IS NULL")
  @Results({
    @Result(property = "recordSortId", column = "income_expend_record_sort_id")
  })
  RecordSort getRecordSortById(@Param("recordSortId") long recordSortId, @Param("user") User user);

  @Update("UPDATE income_expend_record_sort SET icon=#{recordSort.icon},name=#{recordSort.name},type=#{recordSort.type},update_at=#{recordSort.updateAt} WHERE income_expend_record_sort_id=#{recordSort.recordSortId} AND users_id=#{user.userId} AND delete_at IS NULL")
  int update(@Param("recordSort") RecordSort recordSort, @Param("user") User user);

  @Update("UPDATE income_expend_record_sort SET delete_at=#{recordSort.deleteAt} WHERE income_expend_record_sort_id=#{recordSort.recordSortId} AND users_id=#{user.userId} AND delete_at IS NULL")
  int delete(@Param("recordSort") RecordSort recordSort, @Param("user") User user);
}
