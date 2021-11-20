package com.zhangrh.account.javaserver.web.resp;

import java.time.LocalDate;
import java.util.List;

import com.zhangrh.account.javaserver.utils.DateTimeUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDateSortResp implements Comparable<TradeDateSortResp> {
  private String date;
  private List<TradeResp> trades;

  @Override
  public int compareTo(TradeDateSortResp o) {
    long oMill = DateTimeUtil.LocalDateToMill(LocalDate.parse(o.date));
    long thisMill = DateTimeUtil.LocalDateToMill(LocalDate.parse(this.date));
    if ((oMill - thisMill) < 0) {
      return -1;
    } else {
      return 1;
    }
  }
}
