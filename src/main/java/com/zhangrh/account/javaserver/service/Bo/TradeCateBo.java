package com.zhangrh.account.javaserver.service.Bo;

import com.zhangrh.account.javaserver.entity.TradeCate;
import com.zhangrh.account.javaserver.entity.UserToTradeCate;
import com.zhangrh.account.javaserver.enums.TradeCateType;
import com.zhangrh.account.javaserver.enums.TradeOperation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeCateBo extends AbstractBo {
  private long tradeCateId;
  private long userId;
  private long userToTraceCateId;
  private String name;
  private String icon;
  private TradeCateType type;
  private TradeOperation operate;

  public TradeCateBo() {
  }

  public TradeCateBo(TradeCate tradeCate) {
    setTradeCateId(tradeCate.getId());
    setName(tradeCate.getName());
    setIcon(tradeCate.getIcon());
    setType(tradeCate.getType());
    setOperate(tradeCate.getOperate());
  }

  public TradeCate toTradeCate() {
    TradeCate tradeCate = new TradeCate();
    tradeCate.setId(this.getTradeCateId());
    tradeCate.setIcon(this.getIcon());
    tradeCate.setName(this.getName());
    tradeCate.setType(this.getType());
    tradeCate.setOperate(this.getOperate());
    tradeCate.setCreateAt(this.getCreateAt());
    tradeCate.setDeleteAt(this.getDeleteAt());
    tradeCate.setUpdateAt(this.getUpdateAt());
    return tradeCate;
  }

  public UserToTradeCate toUserToTradeCate() {
    UserToTradeCate userToTradeCate = new UserToTradeCate();
    userToTradeCate.setId(this.getUserToTraceCateId());
    userToTradeCate.setUserId(this.getUserId());
    userToTradeCate.setTradeCateId(this.getTradeCateId());
    userToTradeCate.setCreateAt(this.getCreateAt());
    userToTradeCate.setDeleteAt(this.getDeleteAt());
    userToTradeCate.setUpdateAt(this.getUpdateAt());
    return userToTradeCate;
  }
}
