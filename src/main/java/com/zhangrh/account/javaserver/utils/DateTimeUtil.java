package com.zhangrh.account.javaserver.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtil {
  
  /**
   * LocalDateTime转时间戳
   * @param localDateTime
   * @return
   */
  public static long LocalDateTimeToMill(LocalDateTime localDateTime) {
    return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
  }

  /**
   * LocalDate转时间戳
   * @param localDate
   * @return
   */
  public static long LocalDateToMill(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
  }


  /**
   * 时间戳转LocalDate
   * @param mill
   * @return
   */
  public static LocalDate MillToLocalDate(long mill) {
    return Instant.ofEpochMilli(mill).atZone(ZoneOffset.ofHours(8)).toLocalDate();
  }
}
