package com.idealighter.game.dao.logger.po;

import java.util.Date;

import lombok.Data;

@Data
public class GameWinLoseLog {
  private Integer id;
  private Integer game;
  private Integer room;
  private Integer table;
  private Long chips;
  private Long gold;
  private String reason;
  private Long playerId;
  private String playerName;
  private Byte isPlayer;
  private String userName;
  private Integer level;
  private Long actionId;
  private Date time;
  
  private String channelId;
}
