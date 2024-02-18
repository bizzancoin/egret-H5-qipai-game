package com.idealighter.game.common.convert;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateLongConvert {

  public Long toLong(Date timestamp) {
    return timestamp != null ? timestamp.getTime() : null;
  }

  public Date toTimestamp(Long time) {
    return time != null ? new Date(time) : null;
  }
}
