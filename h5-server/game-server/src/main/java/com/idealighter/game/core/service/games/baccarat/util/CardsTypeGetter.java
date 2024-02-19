package com.idealighter.game.core.service.games.baccarat.util;

import com.idealighter.game.core.service.games.baccarat.struct.BaccaratCard;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratCardsType;

import java.util.List;

/**
 * CardsType获取工具类.
 */
public class CardsTypeGetter {

  private CardsTypeGetter() {}

  /**
   * 判断出的牌的类型 .
   * 
   * @param cards .
   * @return
   */
  public static BaccaratCardsType getCardsType(List<BaccaratCard> cards) {
    if (cards == null || cards.size() == 0) {
      throw new IllegalArgumentException("cards不能为null和空");
    }

    // 出牌类型
    BaccaratCardsType cardsType = null;

    if (!isHigCard(cards)) { // 单牌
      cardsType = BaccaratCardsType.HIGH_CARD;
    } else { // 对子
      cardsType = BaccaratCardsType.PAIR;
    }
    return cardsType;
  }

  /**
   * 是否是单牌 .
   * 
   * @param cards .
   * @return
   */
  private static boolean isHigCard(List<BaccaratCard> cards) {

    if (cards.size() == 2) {
      return cards.get(0).power == cards.get(1).power;
    } else {
      return cards.get(0).power == cards.get(1).power || cards.get(0).power == cards.get(2).power
          || cards.get(1).power == cards.get(2).power;
    }
  }

  /**
   * 获取点数 .
   * 
   * 
   * @return
   */
  public static int getCardPoint(List<BaccaratCard> cards) {
    int point = 0;
    // if (cards.size() < 2) {
    // return 0;
    // }
    for (BaccaratCard card : cards) {
      point += card.num;

    }
    return point % 10;
  }

}
