package com.idealighter.game.app.roomctl.dto;

import lombok.Data;

@Data
public class StrategyDto {
  private Long id;
  private Long gold;
  private Long lower;
  private Long upper;
  private Integer ratio;
  private Integer time;
  private Integer withdrawType;
  private String control;
}
