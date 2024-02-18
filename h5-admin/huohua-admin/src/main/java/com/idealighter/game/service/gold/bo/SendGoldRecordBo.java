package com.idealighter.game.service.gold.bo;

import java.util.Date;

import lombok.Data;

@Data
public class SendGoldRecordBo {
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
