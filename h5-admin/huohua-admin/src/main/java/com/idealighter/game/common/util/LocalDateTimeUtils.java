package com.idealighter.game.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class LocalDateTimeUtils {

  /**
   * 转换local DateTime .
   *
   * @author abin
   * @date 2018年9月4日 下午5:29:50
   * @param calendar 日期.
   * @return 本地日期与时间.
   */
  public static LocalDateTime toLocalDateTime(Calendar calendar) {
    LocalDateTime dateTime = LocalDateTime.of(calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND),
        calendar.get(Calendar.MILLISECOND));
    return dateTime;
  }

  /**
   * date 转 本地时间 .
   *
   * @author abin
   * @date 2018年9月5日 下午5:58:21
   * @param date 日期时间.
   * @return 本地时间.
   */
  public static LocalDateTime toLocalDateTime(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    return instant.atZone(zoneId).toLocalDateTime();
  }

  /**
   * 转换 local date .
   *
   * @author abin
   * @date 2018年9月4日 下午5:34:36
   * @param calendar 日期.
   * @return 本地日期.
   */
  public static LocalDate toLocalDate(Calendar calendar) {
    LocalDate date = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
    return date;
  }


  /**
   * date 转 localDate .
   *
   * @author abin
   * @date 2018年9月5日 下午5:55:25
   * @param date 日期.
   * @return 本地日期.
   */
  public static LocalDate toLocalDate(Date date) {
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();
    return instant.atZone(zoneId).toLocalDate();
  }

  /**
   * localdatetime to date .
   *
   * @author abin
   * @date 2018年9月5日 下午8:03:46
   * @param localDateTime 本地日期时间.
   * @return date.
   */
  public static Date toDate(LocalDateTime localDateTime) {
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

    return Date.from(zonedDateTime.toInstant());
  }

  /**
   * 两个时间 相隔多少天 .
   *
   * @author abin
   * @date 2018年9月5日 下午2:40:11
   * @param startDate 开始日期.
   * @param endDate 结束日期.
   * @return 相隔天数.
   */
  public static long diffDay(Calendar startDate, Calendar endDate) {
    LocalDate startLocalDate = toLocalDate(startDate);
    LocalDate endLocalDate = toLocalDate(endDate);
    return ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
  }

}
