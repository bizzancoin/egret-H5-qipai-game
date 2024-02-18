package com.idealighter.game.app.player.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlayerDto {
  private Long playerId;

  private Long superId;

  private String userName;

  private String nickName;

  private String phone;

  private Long totalGold;

  private Long gold;

  private Long safeGold;

  private Byte onlineState;

  private Byte state;

  private Date loginTime;

  private String loginIp;

  private Byte playerType;
  
  private String channelId;
}
