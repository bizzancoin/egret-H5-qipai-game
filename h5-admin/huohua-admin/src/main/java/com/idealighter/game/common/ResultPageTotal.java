package com.idealighter.game.common;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ResultPageTotal<T> {
  private List<T> list;

  private long total;

  private Map<String, Long> statistics;
}
