package com.idealighter.game.robot.common.cards;

import java.util.List;

/**
 * 德州牌值大小比较 .
 */

public class DeckCompare extends CardCompare {

  public DeckCompare(CardType handPowerType, List<CardNumber> tieBreakingInformation,
      List<Card> cardList) {
    super(handPowerType, tieBreakingInformation, cardList);
  }

  @Override
  public int compareTo(CardCompare other) {
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
}
