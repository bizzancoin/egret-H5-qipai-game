package com.idealighter.game.dao.logger.po;

import java.util.Date;

import lombok.Data;

@Data
public class GameGoldLog {
  private Integer id;


  private Long betting;

  private Long bonus;

  private Long profit;

  private Long type;


  private String reason;


  private Long playerId;
  
  private Long superId;


  private Integer playerType;
  
  private String playerName;


  private Boolean isPlayer;


  private String userName;

  private Integer level;


  private Long actionId;


  private Date time;
  
  private String channelId;
}
