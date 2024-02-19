package com.idealighter.game.logger.dao.po;

import java.util.Date;

import lombok.Data;

@Data
public class GameGoldLogPo {
  private Integer id;


  private Long betting;

  private Long bonus;


  private Integer type;


  private String reason;


  private Long playerId;

  private Long superId;


  private String playerName;

  private Boolean isPlayer;

  private Integer playerType;


  private String userName;

  private Integer level;


  private Long actionId;


  private Date time;
  
  
  private String channelId;
}
