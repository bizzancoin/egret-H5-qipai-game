package com.idealighter.game.admin.roomctl.dto;

import java.util.List;

import lombok.Data;

@Data
public class GoldRangePrizePoolDto {
  private Long id;
  private Long lower;
  private Long upper;
  private Long prize;
  private List<StrategyDto> strategys;
}
