package com.idealighter.game.core.service.games.common.niuniu;

import com.google.common.collect.TreeMultiset;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 二人牛牛牌型获取工具类 .
 * 
 * @date 2015年9月16日 下午9:04:27
 *
 */
public class NiuNiuCardsTypeGetter {

  private NiuNiuCardsTypeGetter() {}

  /**
   * 获取牌型的同时将提示牌型计算出来 .
   * 
   * @param cards .
   * @param destBestCards . 需要的目标最优牌组合，如果是牛，前3张是整数，后2张是点数
   * @return
   */
  public static NiuNiuCardsType getCardsType(List<NiuNiuCard> cards,
      List<NiuNiuCard> destBestCards) {
    destBestCards.clear();
    destBestCards.addAll(cards);
    if (isWuXiao(cards)) {
      return NiuNiuCardsType.WU_XIAO;
    } else if (isWuHua(cards)) {
      return NiuNiuCardsType.WU_HUA;
    } else if (isSiZha(cards)) {
      return NiuNiuCardsType.SI_ZHA;
    } else if (isSiHua(cards)) {
      return NiuNiuCardsType.SI_HUA;
    } else {
      destBestCards.clear();
      return getNiuNiuType(cards, destBestCards);
    }
  }

  /**
   * .
   * 
   * @param cards .
   * @return
   */
  public static NiuNiuCardsType getCardsType(List<NiuNiuCard> cards) {
    List<NiuNiuCard> destBestCards = new ArrayList<>();
    destBestCards.addAll(cards);
    if (isWuXiao(cards)) {
      return NiuNiuCardsType.WU_XIAO;
    } else if (isWuHua(cards)) {
      return NiuNiuCardsType.WU_HUA;
    } else if (isSiZha(cards)) {
      return NiuNiuCardsType.SI_ZHA;
    } else if (isSiHua(cards)) {
      return NiuNiuCardsType.SI_HUA;
    } else {
      destBestCards.clear();
      return getNiuNiuType(cards, destBestCards);
    }
  }

  /**
   * 是否是五小,五小：5张牌都小于5,并且全部加起来小于或等于10，有王不能组成五小 .
   * 
   * @param cards .
   */
  public static boolean isWuXiao(List<NiuNiuCard> cards) {
    int totalNum = 0;
    for (NiuNiuCard card : cards) {
      if (card.power >= NiuNiuCard.HEI_TAO_WU.power) {
        return false;
      }

      totalNum += card.num;
    }

    if (totalNum <= 10) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * 是否是五花,五花：5张牌全为花（如Q，J，J，Q，K），有王不能组成五花 .
   * 
   * @param cards .
   * @return
   */
  public static boolean isWuHua(List<NiuNiuCard> cards) {
    for (NiuNiuCard card : cards) {
      if (card.power < NiuNiuCard.HEI_TAO_J.power) {
        return false;
      } else if (card == NiuNiuCard.DA_WANG || card == NiuNiuCard.XIAO_WANG) {
        return false;
      }
    }

    return true;
  }

  /**
   * 是否是四花,四花：5张牌中一张为10，另外4张为花[指J、Q、K]（如10，J，J，Q，K），有王不能组成四花 .
   * 
   * @param cards .
   * @return
   */
  public static boolean isSiHua(List<NiuNiuCard> cards) {
    // 10牌的个数
    int cardTenNum = 0;

    for (NiuNiuCard card : cards) {
      if (card.power == NiuNiuCard.HEI_TAO_SHI.power) {
        cardTenNum += 1;
      } else if (card.power < NiuNiuCard.HEI_TAO_SHI.power) {
        return false;
      } else if (card == NiuNiuCard.DA_WANG || card == NiuNiuCard.XIAO_WANG) {
        return false;
      }
    }

    if (cardTenNum == 1) {
      return true;
    }

    return false;
  }

  /**
   * 是否四炸,四炸：5张牌中有4张一样的牌，此时无需有牛。若庄家闲家都是四炸牌型，则比较4张一样的牌的大小。 .
   * 
   * @param cards .
   * @return
   */
  public static boolean isSiZha(List<NiuNiuCard> cards) {
    TreeMultiset<Integer> cardPowers = TreeMultiset.create();
    for (NiuNiuCard card : cards) {
      cardPowers.add(card.power);
    }

    int firstCardPowerCount = cardPowers.firstEntry().getCount();
    if (cardPowers.elementSet().size() == 2
        && (firstCardPowerCount == 1 || firstCardPowerCount == 4)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 只获取牛牛类型 .
   * 
   * @param cards .
   * @param destTipCards .
   * @return
   */
  private static NiuNiuCardsType getNiuNiuType(List<NiuNiuCard> cards,
      List<NiuNiuCard> destTipCards) {
    // 王的数量
    int kindNum = 0;
    // 牌数字的合计
    int totalNum = 0;

    for (NiuNiuCard card : cards) {
      totalNum += card.num;
      if (card == NiuNiuCard.DA_WANG || card == NiuNiuCard.XIAO_WANG) {
        kindNum += 1;
      }
    }

    if (kindNum == 0) { // 没有王,大小是固定的
      // 用于计算 提示牌(前3张是整数，后2张是点数)
      LinkedHashSet<NiuNiuCard> destTipCardsTemp = new LinkedHashSet<>();
      for (int i = 0; i < cards.size(); i++) {
        for (int j = i + 1; j < cards.size(); j++) {
          for (int k = j + 1; k < cards.size(); k++) {
            int numCount = cards.get(i).num + cards.get(j).num + cards.get(k).num;
            if (numCount % 10 == 0) {
              destTipCardsTemp.add(cards.get(i));
              destTipCardsTemp.add(cards.get(j));
              destTipCardsTemp.add(cards.get(k));
              destTipCardsTemp.addAll(cards);

              destTipCards.addAll(destTipCardsTemp);

              int niu = (totalNum - numCount) % 10;
              if (niu == 0) {
                return NiuNiuCardsType.geCardsType(10);
              } else {
                return NiuNiuCardsType.geCardsType(niu);
              }

            }
          }
        }
      }
    } else if (kindNum == 1) { // 有一张王， 必是有牛 先从剩余四张中选出3张加起来为整，找不到再从剩余四张中选出两张加起来最大

      // 用于计算 提示牌(前3张是整数，后2张是点数)
      List<NiuNiuCard> destTipCardsTemp = new ArrayList<>();
      destTipCardsTemp.addAll(cards);
      destTipCardsTemp.remove(NiuNiuCard.DA_WANG);
      destTipCardsTemp.remove(NiuNiuCard.XIAO_WANG);

      /*
       * 先从剩余四张中选出3张加起来为整,是牛牛 .
       */
      for (int i = 0; i < destTipCardsTemp.size(); i++) {
        for (int j = i + 1; j < destTipCardsTemp.size(); j++) {
          for (int k = j + 1; k < destTipCardsTemp.size(); k++) {
            NiuNiuCard card1 = destTipCardsTemp.get(i);
            NiuNiuCard card2 = destTipCardsTemp.get(j);
            NiuNiuCard card3 = destTipCardsTemp.get(k);

            int numCount = card1.num + card2.num + card3.num;
            if (numCount % 10 == 0) {
              destTipCards.add(card1);
              destTipCards.add(card2);
              destTipCards.add(card3);
              for (NiuNiuCard card : cards) {
                if (!destTipCards.contains(card)) {
                  destTipCards.add(card);
                }
              }

              return NiuNiuCardsType.NIU_NIU;
            }
          }
        }
      }

      /*
       * 剩余四张中选出两张加起来最大 .
       */
      int maxNiu = 0;
      // 点数牌1
      NiuNiuCard pointCard1 = null;
      // 点数牌2
      NiuNiuCard pointCard2 = null;

      for (int i = 0; i < destTipCardsTemp.size(); i++) {
        for (int j = i + 1; j < destTipCardsTemp.size(); j++) {
          int niu = (destTipCardsTemp.get(i).num + destTipCardsTemp.get(j).num) % 10;
          if (niu == 0) {
            niu = 10;
          }
          if (niu > maxNiu) {
            pointCard1 = destTipCardsTemp.get(i);
            pointCard2 = destTipCardsTemp.get(j);
            maxNiu = niu;
          }
        }
      }

      for (NiuNiuCard card : cards) {
        if (card != pointCard1 && card != pointCard2) {
          destTipCards.add(card);
        }
      }

      destTipCards.add(pointCard1);
      destTipCards.add(pointCard2);


      return NiuNiuCardsType.geCardsType(maxNiu);
    } else if (kindNum == 2) { // 有两张王，必是牛牛
      destTipCards.add(NiuNiuCard.DA_WANG);
      // 用于计算 提示牌(前3张是整数，后2张是点数)
      for (NiuNiuCard card : cards) {
        if (card != NiuNiuCard.DA_WANG && card != NiuNiuCard.XIAO_WANG) {
          destTipCards.add(card);
        }
      }
      destTipCards.add(NiuNiuCard.XIAO_WANG);

      return NiuNiuCardsType.NIU_NIU;
    }

    destTipCards.addAll(cards);
    return NiuNiuCardsType.MEI_NIU;

  }
}
