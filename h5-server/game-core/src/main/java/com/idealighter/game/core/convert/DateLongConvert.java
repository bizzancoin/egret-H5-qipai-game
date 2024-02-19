package com.idealighter.game.core.convert;

import java.util.Date;


public class DateLongConvert {

  public Long toLong(Date timestamp) {
    return timestamp != null ? timestamp.getTime() : null;
  }

  public Date toTimestamp(Long time) {
    return time != null ? new Date(time) : null;
  }
}
