package com.idealighter.game.core.convert;

import java.util.concurrent.atomic.AtomicLong;


public class LongAtomicLongConvert {

  public long toLong(AtomicLong value) {
    return value != null ? value.get() : 0L;
  }

  public AtomicLong toAtomicLong(long value) {
    return value != 0L ? new AtomicLong(value) : new AtomicLong(0L);
  }
}
