package com.zhangrh.account.javaserver.mapper;


import java.util.List;

import com.zhangrh.account.javaserver.entity.AccountDefault;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface AccountDefaultMapper {
  @Select("SELECT * FROM account_default")
  @Results({
    @Result(property = "createAt", column = "create_at"),
    @Result(property = "updateAt", column = "update_at"),
    @Result(property = "deleteAt", column = "delete_at"),
  })
  List<AccountDefault> query();
}
