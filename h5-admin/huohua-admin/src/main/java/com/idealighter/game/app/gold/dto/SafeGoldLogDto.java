package com.idealighter.game.app.gold.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SafeGoldLogDto {
  private Integer id;


  private Long before;

  private Long after;


  private Long change;


  private String reason;


  private Long playerId;

  private Long superId;


  private String playerName;


  private Boolean isPlayer;


  private String userName;

  private Integer level;


  private Long actionId;


  private Date time;
  
  private String channelId;
  
  private String orderNo;
}
