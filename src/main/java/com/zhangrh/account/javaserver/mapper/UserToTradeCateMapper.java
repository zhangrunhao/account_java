package com.zhangrh.account.javaserver.mapper;

import java.util.List;

import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.entity.UserToTradeCate;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserToTradeCateMapper {

  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Insert("INSERT INTO user_to_trade_cate (user_id, trade_cate_id, create_at) VALUES (#{p.userId}, #{p.tradeCateId}, #{p.createAt})")
  int insert(@Param("p") UserToTradeCate userToTradeCate);

  @Select("SELECT * FROM user_to_trade_cate WHERE user_id=#{user.id}")
  @Results({
    @Result(property = "userId", column = "user_id"),
    @Result(property = "tradeCateId", column = "trade_cate_id"),
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<UserToTradeCate> queryUser(@Param("user") User user);

  @Update("UPDATE user_to_trade_cate SET delete_at=#{p.deleteAt} WHERE user_id=#{p.userId} AND trade_cate_id=#{p.tradeCateId}")
  int delete(@Param("p") UserToTradeCate userToTradeCate);
}
