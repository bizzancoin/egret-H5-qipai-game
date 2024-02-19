package com.idealighter.game.logger.service.bo;

import java.util.Date;

import lombok.Data;

@Data
public class GameGoldLogBo {
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
