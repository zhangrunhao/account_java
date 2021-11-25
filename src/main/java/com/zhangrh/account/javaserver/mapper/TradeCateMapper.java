package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.TradeCate;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TradeCateMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO trade_cate (icon, name, type, operate, create_at) VALUES (#{tradeCate.icon}, #{tradeCate.name}, #{tradeCate.type}, #{tradeCate.operate}, #{tradeCate.createAt})")
  int insert(@Param("tradeCate") TradeCate tradeCate);

  @Select("SELECT * FROM trade_cate WHERE id=#{tradeCate.id}")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  TradeCate queryId(@Param("tradeCate") TradeCate tradeCate);


  @Select("SELECT * FROM trade_cate WHERE operate=#{operate}")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  TradeCate queryOperate(@Param("operate") int operate);

  @Select("SELECT * FROM trade_cate WHERE type=#{type}")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<TradeCate> queryType(@Param("type") int type);

  @Update("UPDATE trade_cate SET name=#{tradeCate.name},icon=#{tradeCate.icon},type=#{tradeCate.type},operate=#{tradeCate.operate}, update_at=#{tradeCate.updateAt} WHERE id=#{tradeCate.id}")
  int update(@Param("tradeCate") TradeCate tradeCate);

  @Update("UPDATE trade_cate SET delete_at=#{tradeCate.deleteAt} WHERE id=#{tradeCate.id}")
  int delete(@Param("tradeCate") TradeCate tradeCate);
}
