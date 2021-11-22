package com.zhangrh.account.javaserver.mapper;

import com.zhangrh.account.javaserver.entity.Transfer;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

public interface TransferMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO transfer (out_trade_id, in_trade_id, create_at) VALUES (#{p.outTradeId}, #{p.inTradeId}, #{p.createAt})")
  int insert(@Param("p") Transfer transfer);

  @Select("SELECT * FROM transfer WHERE out_trade_id=#{id}")
  @Results({
      @Result(property = "outTradeId", column = "out_trade_id"),
      @Result(property = "inTradeId", column = "in_trade_id"),
      @Result(property = "spendDate", column = "spend_date"),
      @Result(property = "createAt", column = "create_at"),
      @Result(property = "updateAt", column = "update_at"),
      @Result(property = "deleteAt", column = "delete_at")
  })
  Transfer queryByOutTradeId(@Param("id") long outTradeId);

  @Select("SELECT * FROM transfer WHERE in_trade_id=#{id}")
  @Results({
      @Result(property = "outTradeId", column = "out_trade_id"),
      @Result(property = "inTradeId", column = "in_trade_id"),
      @Result(property = "spendDate", column = "spend_date"),
      @Result(property = "createAt", column = "create_at"),
      @Result(property = "updateAt", column = "update_at"),
      @Result(property = "deleteAt", column = "delete_at")
  })
  Transfer queryByInTradeId(@Param("id") long inTradeId);

  @Update("UPDATE transfer SET delete_at=#{t.deleteAt} WHERE id=#{t.id};")
  int delete(@Param("t") Transfer transfer);
}
