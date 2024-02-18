package com.idealighter.game.service.personctl.bo;

import lombok.Data;

@Data
public class PlayerCtrlBasicInfoBo {
  private Long playerId;
  private Long superId;
  private String nickName;
  private Integer gameId;
  private Integer roomId;
  private Long gold;
  private Long safeGold;
  private Long todayBetResult = 0L;
  private Long yesTodayBetResult = 0L;
  private Long prizeTotal;
}
