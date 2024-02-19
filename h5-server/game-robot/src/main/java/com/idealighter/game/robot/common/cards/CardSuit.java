package com.idealighter.game.robot.common.cards;

/**
 * 牌花色 .
 */

public enum CardSuit {
  DIAMOND("\u2666", 1), // 方块
  CLUB("\u2663", 2), // 草花
  HEART("\u2665", 3), // 红桃
  SPADE("\u2660", 4);// 黑桃

  private final String symbol;
  private final int power;

  private CardSuit(String symbol, int power) {
    this.symbol = symbol;
    this.power = power;
  }

  @Override
  public String toString() {
    return symbol;
  }

  /**
   * 通过ID找到对应的牌 .
   *
   * @param suitType . .
   * @return .
   */
  public static CardSuit getCardSuit(int suitType) {
    if (suitType < 0 || suitType > 3) {
      return null;
    }
    CardSuit suit = null;
    switch (suitType) {
      case 0:
        suit = CardSuit.DIAMOND;
        break;
      case 1:
        suit = CardSuit.CLUB;
        break;
      case 2:
        suit = CardSuit.HEART;
        break;
      case 3:
        suit = CardSuit.SPADE;
        break;
      default:
        break;
    }
    return suit;
  }

  public int getPower() {
    return power;
  }
}
