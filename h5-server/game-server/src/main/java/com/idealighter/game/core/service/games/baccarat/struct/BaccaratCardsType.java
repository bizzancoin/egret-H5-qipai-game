package com.idealighter.game.core.service.games.baccarat.struct;

/**
 * 牌型 .
 */
public enum BaccaratCardsType {

  HIGH_CARD(1), // 单牌：单张牌
  PAIR(2), // 对牌：两个
  ;
  public final int type;

  private BaccaratCardsType(int type) {
    this.type = type;
  }

}
