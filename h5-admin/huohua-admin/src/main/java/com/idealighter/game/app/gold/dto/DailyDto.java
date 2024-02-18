package com.idealighter.game.app.gold.dto;

import lombok.Data;

@Data
public class DailyDto {
  private String time;
  
  private Long allBetting;
  
  private Long allBonus;
  
  private Long allProfit;
  
  private Long allSendGold;
  
  private Long allTransferInGold;
  
  private Long allTransferOutGold;
  
  private Long allInoutProfit;
}
