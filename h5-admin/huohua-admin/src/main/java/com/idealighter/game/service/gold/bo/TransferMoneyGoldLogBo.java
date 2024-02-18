package com.idealighter.game.service.gold.bo;

import java.util.Date;

import lombok.Data;

@Data
public class TransferMoneyGoldLogBo {

  private Integer id;

  private String orderNo;

  private Long price;

  private Long beforeBalance;

  private Long afterBalance;

  private Long beforeSafeGold;

  private Long afterSafeGold;

  private Byte flag;

  private Long playerId;

  private Long superId;


  private String playerName;


  private Boolean isPlayer;


  private String userName;


  private Integer level;

  private Long actionId;

  private Date time;
  
  private String channelId;
}
