package com.idealighter.game.app.roomctl.dto;

import lombok.Data;

@Data
public class ModifyPrizePoolDto {
  private Integer gameId;
  private Integer roomId;
  private Long prizePoolId;
  private StrategyDto strategy;
}
