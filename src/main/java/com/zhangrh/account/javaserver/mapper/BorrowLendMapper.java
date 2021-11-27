package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.BorrowLend;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BorrowLendMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO borrow_lend (borrow_lend_trade_id, repayment_receive_trade_id, create_at) VALUES (#{p.borrowLendTradeId}, #{p.repaymentReceiveTradeId}, #{p.createAt})")
  int insert(@Param("p") BorrowLend borrowLend);

  @Select("SELECT * FROM borrow_lend WHERE borrow_lend_trade_id=#{borrowLend.borrowLendTradeId}")
  @Results({
    @Result(property = "borrowLendTradeId", column = "borrow_lend_trade_id"),
    @Result(property = "repaymentReceiveTradeId", column = "repayment_receive_trade_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<BorrowLend> queryBorrowLend(@Param("p") BorrowLend borrowLend);

  @Select("SELECT * FROM borrow_lend WHERE repayment_receive_trade_id=#{borrowLend.repaymentReceiveTradeId}")
  @Results({
    @Result(property = "borrowLendTradeId", column = "borrow_lend_trade_id"),
    @Result(property = "repaymentReceiveTradeId", column = "repayment_receive_trade_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<BorrowLend> queryRepaymentReceive(@Param("p") BorrowLend borrowLend);

  @Update("UPDATE borrow_lend SET delete_at=#{p.deleteAt} WHERE repayment_receive_trade_id=#{p.repaymentReceiveTradeId}")
  int deleteByRepaymentReceive(@Param("p") BorrowLend borrowLend);

  @Update("UPDATE borrow_lend SET delete_at=#{p.deleteAt} WHERE borrow_lend_trade_id=#{p.borrowLendTradeId}")
  int deleteByBorrowLend(@Param("p") BorrowLend borrowLend);
}
