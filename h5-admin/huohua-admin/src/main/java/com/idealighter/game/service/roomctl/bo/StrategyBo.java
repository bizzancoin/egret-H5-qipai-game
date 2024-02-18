package com.idealighter.game.service.roomctl.bo;

import lombok.Data;

@Data
public class StrategyBo {
  private Long id;
  private Long gold;
  private Long lower;
  private Long upper;
  private Integer ratio;
  private Integer time;
  private Integer withdrawType;
  private String control;
}
