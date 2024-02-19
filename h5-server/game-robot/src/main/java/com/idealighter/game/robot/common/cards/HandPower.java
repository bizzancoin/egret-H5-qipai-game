package com.idealighter.game.robot.common.cards;

import java.util.List;

/**
 * Created by Administrator on 2015/10/29.
 */
public class HandPower implements Comparable<HandPower> {
  private final CardType handPowerType;
  private final List<CardNumber> tieBreakingInformation;

  public HandPower(final CardType handPowerType, final List<CardNumber> tieBreakingInformation) {
    this.handPowerType = handPowerType;
    this.tieBreakingInformation = tieBreakingInformation;
  }

  @Override
  public int compareTo(HandPower other) {
    int typeDifference = handPowerType.getPower() - other.handPowerType.getPower();
    if (typeDifference == 0) {
      for (int i = 0; i < tieBreakingInformation.size(); i++) {
        int tieDifference = tieBreakingInformation.get(i).getPower()
            - other.tieBreakingInformation.get(i).getPower();
        if (tieDifference != 0) {
          return tieDifference;
        }
      }
      return 0;
    }

    return typeDifference;
  }

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
