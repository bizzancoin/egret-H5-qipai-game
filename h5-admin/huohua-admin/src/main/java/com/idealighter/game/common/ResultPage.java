package com.idealighter.game.common;

import java.util.List;

import lombok.Data;

@Data
public class ResultPage<T> {
  private List<T> list;

  private long total;
}
