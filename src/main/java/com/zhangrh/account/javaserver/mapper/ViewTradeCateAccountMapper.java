package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.ViewTradeCateAccount;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

public interface ViewTradeCateAccountMapper {

    @Select("SELECT * FROM v_trade_cate_account WHERE trade_id=#{id}")
    @Results({ @Result(property = "tradeId", column = "trade_id"), @Result(property = "userId", column = "users_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "tradeCateId", column = "trace_cate_id"), @Result(property = "money", column = "money"),
            @Result(property = "remark", column = "remark"), @Result(property = "spendDate", column = "spend_date"),
            @Result(property = "operate", column = "operate"),
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "tradeCateName", column = "trade_cate_name"),
            @Result(property = "accountIcon", column = "account_icon"),
            @Result(property = "tradeCateIcon", column = "trade_cate_icon"),
            @Result(property = "createAt", column = "create_at"), @Result(property = "updateAt", column = "update_at"),
            @Result(property = "deleteAt", column = "delete_at"), })
    List<ViewTradeCateAccount> queryByTradeId(@Param("id") long id);

    @Select("SELECT * FROM v_trade_cate_account WHERE account_id=#{id}")
    @Results({ @Result(property = "tradeId", column = "trade_id"), @Result(property = "userId", column = "users_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "tradeCateId", column = "trace_cate_id"), @Result(property = "money", column = "money"),
            @Result(property = "remark", column = "remark"), @Result(property = "spendDate", column = "spend_date"),
            @Result(property = "operate", column = "operate"),
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "tradeCateName", column = "trade_cate_name"),
            @Result(property = "accountIcon", column = "account_icon"),
            @Result(property = "tradeCateIcon", column = "trade_cate_icon"),
            @Result(property = "createAt", column = "create_at"), @Result(property = "updateAt", column = "update_at"),
            @Result(property = "deleteAt", column = "delete_at"), })
    List<ViewTradeCateAccount> queryByAccountId(@Param("id") long id);

    @Select("SELECT * FROM v_trade_cate_account WHERE user_id=#{id}")
    @Results({ @Result(property = "tradeId", column = "trade_id"), @Result(property = "userId", column = "users_id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "tradeCateId", column = "trace_cate_id"), @Result(property = "money", column = "money"),
            @Result(property = "remark", column = "remark"), @Result(property = "spendDate", column = "spend_date"),
            @Result(property = "operate", column = "operate"),
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "tradeCateName", column = "trade_cate_name"),
            @Result(property = "accountIcon", column = "account_icon"),
            @Result(property = "tradeCateIcon", column = "trade_cate_icon"),
            @Result(property = "createAt", column = "create_at"), @Result(property = "updateAt", column = "update_at"),
            @Result(property = "deleteAt", column = "delete_at"), })
    List<ViewTradeCateAccount> queryByUserId(@Param("id") long id);
}
