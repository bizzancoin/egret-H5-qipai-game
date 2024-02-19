package com.idealighter.game.robot.common.cards;

public enum CardType {
  /**
   * 皇家同花顺(royal flush)：由AKQJ10五张组成，并且这5张牌花色相同   同花顺(straight flush)：由五张连张同花色的牌组成 
   * 4条(four of a kind)：4张同点值的牌加上一张其他任何牌    葫芦(full house)：3张同点值加上另外一对   同花(flush)：
   * 5张牌花色相同，但是不成顺子    顺子(straight)：五张牌连张，至少一张花色不同  3条(three of a kind)：三张牌点值相同，其他两张各异  
   * 两对(two pairs)：两对加上一个杂牌    一对(one pair)：一对加上3张杂牌  高牌(high card)：不符合上面任何一种牌型的牌型， 由单牌且不连续不同花的组成  .
   */
  HIGH_CARD(1), ONE_PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5), FLUSH(6), FULL_HOUSE(
      7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9);

  private final int power;

  private CardType(int power) {
    this.power = power;
  }

  public int getPower() {
    return power;
  }

}
