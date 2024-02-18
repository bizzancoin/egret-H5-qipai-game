package com.idealighter.game.app.personctl.dto;

import lombok.Data;

@Data
public class PlayerCtrlBasicInfoDto {
  private Long playerId;
  private Long superId;
  private String nickName;
  private Long gameId;
  private Long roomId;
  private Long gold;
  private Long safeGold;
  private Long todayBetResult;
  private Long yesTodayBetResult;
  private Long prizeTotal;
}
