package com.idealighter.game.core.convert;

import java.util.Date;


public class DateLongValueConvert {

  public long toLong(Date timestamp) {
    return timestamp != null ? timestamp.getTime() : 0L;
  }

  public Date toTimestamp(Long time) {
    return time != 0L ? new Date(time) : null;
  }
}
