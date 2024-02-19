package com.idealighter.game.core.service.player.service.bo;

import lombok.Data;

@Data
public class PlayerRankBo {

  private long playerId;

  private long superId;

  private String playerName;

  private int icon;

  private int vipLevel;

  private long gold;

  private long winGold;
}
