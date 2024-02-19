package com.idealighter.game.admin.roomctl.dto;

import java.util.List;

import lombok.Data;

@Data
public class DefaultPrizePoolDto {
  private Long prize;
  private List<StrategyDto> strategys;
}
