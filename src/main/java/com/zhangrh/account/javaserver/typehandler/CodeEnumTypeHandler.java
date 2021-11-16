package com.zhangrh.account.javaserver.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhangrh.account.javaserver.enums.BaseCodeEnum;
import com.zhangrh.account.javaserver.utils.CodeEnumUtil;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(BaseCodeEnum.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class CodeEnumTypeHandler<E extends Enum<?> & BaseCodeEnum> extends BaseTypeHandler<BaseCodeEnum> {

  private Class<E> type;

  public CodeEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  // 执行sql时转换参数
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, BaseCodeEnum parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  // 处理返回结果
  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    return rs.wasNull() ? null : codeOf(code);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return rs.wasNull() ? null : codeOf(code);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return cs.wasNull() ? null : codeOf(code);
  }

  private E codeOf(int code) {
    try {
      return CodeEnumUtil.codeOf(type, code);
    } catch (Exception ex) {
      throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.",
          ex);
    }
  }
}
