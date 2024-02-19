package com.idealighter.game.core.service.games.common.niuniu;

import java.util.List;

/**
 * 牌型比较工具类 .
 * 
 * @date 2015年9月16日 下午10:09:03
 *
 */
public class NiuNiuCardsTypeComparator {

  private NiuNiuCardsTypeComparator() {}

  /**
   * 比较大小 .
   * 
   * @param myCards .
   * @param myCardsType .
   * @param otherCards .
   * @param otherCardsType .
   * @return true:我大 false：庄家大
   */
  public static boolean compare(List<NiuNiuCard> myCards, NiuNiuCardsType myCardsType,
      List<NiuNiuCard> otherCards, NiuNiuCardsType otherCardsType) {

    if (myCardsType.niu > otherCardsType.niu) {
      return true;
    } else if (myCardsType.niu < otherCardsType.niu) {
      return false;
    } else { // 同牌型的有王的比没王的小
      if (!myCards.contains(NiuNiuCard.DA_WANG) && !myCards.contains(NiuNiuCard.XIAO_WANG)
          && (otherCards.contains(NiuNiuCard.DA_WANG)
              || otherCards.contains(NiuNiuCard.XIAO_WANG))) {
        return true;
      }

      if (!otherCards.contains(NiuNiuCard.DA_WANG) && !otherCards.contains(NiuNiuCard.XIAO_WANG)
          && (myCards.contains(NiuNiuCard.DA_WANG) || myCards.contains(NiuNiuCard.XIAO_WANG))) {
        return false;
      }

      NiuNiuCard myMaxCard = getMaxCard(myCards);
      NiuNiuCard dealerMaxCard = getMaxCard(otherCards);

      if (myMaxCard.power > dealerMaxCard.power) {
        return true;
      } else if (myMaxCard.power < dealerMaxCard.power) {
        return false;
      } else {
        return myMaxCard.type.ordinal() > dealerMaxCard.type.ordinal();
      }
    }
  }

  /**
   * 获取最大的牌 .
   * 
   * @param cards .
   * @return
   */
  private static NiuNiuCard getMaxCard(List<NiuNiuCard> cards) {
    NiuNiuCard maxCard = null;
    for (NiuNiuCard card : cards) {
      if (maxCard == null || card.power > maxCard.power
          || (card.power == maxCard.power && card.type.ordinal() > maxCard.type.ordinal())) {
        maxCard = card;
      }
    }

    return maxCard;
  }

}
