package com.idealighter.game.service.gold.bo;

import lombok.Data;

@Data
public class DailyBo {
  private String time;

  private Long allBetting;

  private Long allBonus;

  private Long allProfit;

  private Long allSendGold;

  private Long allTransferInGold;

  private Long allTransferOutGold;

  private Long allInoutProfit;

  /**
   * 初始化 .
   *
   * @author abin
   * @date 2018年9月5日 下午4:45:17
   */
  public void init() {
    this.time = "";
    this.allBetting = 0L;
    this.allBonus = 0L;
    this.allProfit = 0L;
    this.allSendGold = 0L;
    this.allTransferInGold = 0L;
    this.allTransferOutGold = 0L;
    this.allInoutProfit = 0L;
  }
}
