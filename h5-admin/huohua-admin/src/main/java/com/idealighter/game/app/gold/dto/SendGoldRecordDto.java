package com.idealighter.game.app.gold.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SendGoldRecordDto {

  private Integer id;


  private Long playerId;

  private Long superId;

  private String playerName;


  private Integer adminId;

  private String adminName;

  private Long gold;

  private Long safeGold;


  private Date time;
  
  private String channelId;
  
}
