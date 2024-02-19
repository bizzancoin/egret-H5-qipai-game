package com.idealighter.game.robot.common.cards;

import java.util.List;

/**
 * 牌型计算器.
 */

public abstract class CardCompare implements Comparable<CardCompare> {
  public final CardType handPowerType;
  public final List<CardNumber> tieBreakingInformation;
  public final List<Card> cardList;

  /**
   * 构造函数.
   * 
   * @param handPowerType .
   * @param tieBreakingInformation .
   * @param cardList .
   */
  public CardCompare(final CardType handPowerType, final List<CardNumber> tieBreakingInformation,
      List<Card> cardList) {
    this.handPowerType = handPowerType;
    this.tieBreakingInformation = tieBreakingInformation;
    this.cardList = cardList;
  }

  @Override
  public abstract int compareTo(CardCompare other);


  @Override
  public String toString() {
    return handPowerType.toString() + " " + tieBreakingInformation.toString();
  }

  public CardType getHandPowerType() {
    return handPowerType;
  }

  public List<CardNumber> getTieBreakingInformation() {
    return tieBreakingInformation;
  }

}
