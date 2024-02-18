package com.idealighter.game.app.gold.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GameGoldLogDto {
  private Integer id;


  private Long betting;

  private Long bonus;
  
  private Long profit;


  private Long type;


  private String reason;


  private Long playerId;
  
  private Long superId;


  private String playerName;
  
  private Integer playerType;


  private Boolean isPlayer;


  private String userName;

  private Integer level;


  private Long actionId;


  private Date time;
  
  private String channelId;
}
