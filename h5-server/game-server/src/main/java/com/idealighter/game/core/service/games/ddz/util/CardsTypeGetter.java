package com.idealighter.game.core.service.games.ddz.util;

import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.TreeMultiset;
import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import com.idealighter.game.core.service.games.ddz.struct.DdzCardsType;
import java.util.List;
import java.util.TreeSet;

/**
 * CardsType获取工具类 .
 * 
 * @date 2015年8月16日 下午1:04:45
 *
 */
public class CardsTypeGetter {

  private CardsTypeGetter() {}

  /**
   * 判断出的牌的类型.
   * 
   * @param cards ..
   * @return .
   */
  public static DdzCardsType getCardsType(List<DdzCard> cards) {
    if (cards == null || cards.size() == 0) {
      throw new IllegalArgumentException("cards不能为null和空");
    }

    // 出牌类型
    DdzCardsType cardsType = null;

    if (isDan(cards)) { // 单牌
      cardsType = DdzCardsType.DAN;
    } else if (isWangZha(cards)) { // 火箭：即双王（双花牌），什么牌型都可打，是最大的牌
      cardsType = DdzCardsType.WANG_ZHA;
    } else if (isDuiZi(cards)) { // 对牌：两个
      cardsType = DdzCardsType.DUI_ZI;
    } else if (isZhaDan(cards)) { // 四张牌点相同的牌（如四个8）。除火箭和比自己大的炸弹外，什么牌型都可打
      cardsType = DdzCardsType.ZHA_DAN;
    } else if (isSanBuDai(cards)) { // 三张牌：三张牌点相同的牌。
      cardsType = DdzCardsType.SAN_BU_DAI;
    } else if (isSanDaiDan(cards)) { // 三带单：三张牌 ＋ 一张单牌。例如： 888+9
      cardsType = DdzCardsType.SAN_DAI_DAN;
    } else if (isSanDaiDui(cards)) { // 三带对：三张牌 ＋ 一对牌。例如： 888+99
      cardsType = DdzCardsType.SAN_DAI_DUI;
    } else if (isShunZi(cards)) { // 单顺：五张或更多连续的单牌。例如：3+4+5+6+7+8。不包括2和大、小王。
      cardsType = DdzCardsType.SHUN_ZI;
    } else if (isShuangShun(cards)) { // 双顺：三个或更多连续的对牌。例如：33+44+55。不包括2和大、小王。
      cardsType = DdzCardsType.SHUANG_SHUN;
    } else if (isSanShun(cards)) { // 三顺：二个或更多连续的三张牌。例如：333444、444555666777。不包括2和大、小王。
      cardsType = DdzCardsType.SAN_SHUN;
    } else if (isFeiJiDaiDan(cards)) { // 飞机带单翅膀：三顺＋同数量的单套牌。例如：333444+69 ，333444+66也是合法的
      cardsType = DdzCardsType.FEI_JI_DAI_DAN;
    } else if (isFeiJiDaiDui(cards)) { // 飞机带双翅膀：三顺＋同数量的对套牌。例如：333444555+667799
      cardsType = DdzCardsType.FEI_JI_DAI_DUI;
    } else if (isSiDaiDan(cards)) { // 四张牌＋任意两套张数相同的单牌。例如：5555＋3＋8
      cardsType = DdzCardsType.SI_DAI_DAN;
    } else if (isSiDaiDui(cards)) { // 四张牌＋任意两套张数相同的对牌。例如：4444＋55＋77
      cardsType = DdzCardsType.SI_DAI_DUI;
    }

    return cardsType;
  }

  /**
   * 是否是单牌.
   * 
   * @param cards ..
   * @return .
   */
  private static boolean isDan(List<DdzCard> cards) {

    return cards.size() == 1;
  }

  /**
   * 是否是王炸,王炸不是对子,小王:52,大王 . :53.
   * 
   * @param cards ..
   * @return .
   */
  private static boolean isWangZha(List<DdzCard> cards) {

    return cards.size() == 2 && cards.get(0).ordinal()
        + cards.get(1).ordinal() == DdzCard.XIAO_WANG.ordinal() + DdzCard.DA_WANG.ordinal();
  }

  /**
   * 是否是对子,对子不包括王炸.
   * 
   * @param cards .
   */
  private static boolean isDuiZi(List<DdzCard> cards) {

    if (cards.size() == 2) {
      DdzCard card1 = cards.get(0);
      DdzCard card2 = cards.get(1);

      if (card1.num == card2.num && card1.num != DdzCard.XIAO_WANG.num) {
        return true;
      }
    }

    return false;
  }

  /**
   * 是否是炸弹,炸弹不包括王炸 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isZhaDan(List<DdzCard> cards) {
    if (cards.size() == 4) {
      DdzCard card1 = cards.get(0);
      DdzCard card2 = cards.get(1);
      DdzCard card3 = cards.get(2);
      DdzCard card4 = cards.get(3);

      if (card1.num == card2.num && card2.num == card3.num && card3.num == card4.num) {
        return true;
      }
    }

    return false;
  }

  /**
   * 是否是三不带 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSanBuDai(List<DdzCard> cards) {

    return cards.size() == 3 && cards.get(0).num == cards.get(1).num
        && cards.get(1).num == cards.get(2).num;
  }

  /**
   * 是否是三带单 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSanDaiDan(List<DdzCard> cards) {

    if (cards.size() == 4) {
      // 牌的数字集合
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      int firstCardNumCount = cardNums.firstEntry().getCount();
      int lastCardNumCount = cardNums.lastEntry().getCount();
      if ((firstCardNumCount == 1 && lastCardNumCount == 3)
          || (firstCardNumCount == 3 && lastCardNumCount == 1)) {
        return true;
      }
    }

    return false;

  }

  /**
   * 是否三带一对 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSanDaiDui(List<DdzCard> cards) {
    if (cards.size() == 5) {
      // 牌的数字集合
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      if (cardNums.elementSet().size() == 2) {
        // 排序后第一张牌出现的次数
        int firstCardCount = cardNums.firstEntry().getCount();
        if (firstCardCount == 2 || firstCardCount == 3) {
          return true;
        }
      }

    }

    return false;
  }

  /**
   * 是否顺子 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isShunZi(List<DdzCard> cards) {
    int size = cards.size();

    if (size >= 5 && size <= 12) {
      TreeSet<Integer> cardNums = new TreeSet<>();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      return !cardNums.contains(DdzCard.XIAO_WANG.num)
          && !cardNums.contains(DdzCard.FANG_KUAI_ER.num)
          && cardNums.last() - cardNums.first() + 1 == cards.size()
          && cardNums.size() == cards.size();
    }

    return false;
  }

  /**
   * 是否是双顺 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isShuangShun(List<DdzCard> cards) {
    int size = cards.size();

    if (size % 2 == 0 && size >= 6 && size <= 20) {

      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      Integer firstCardNum = cardNums.firstEntry().getElement();
      for (int i = 0; i < size / 2; i++) {
        if (cardNums.count(firstCardNum + i) != 2 || cardNums.contains(DdzCard.XIAO_WANG.num)
            || cardNums.contains(DdzCard.FANG_KUAI_ER.num)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * 是否是三顺 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSanShun(List<DdzCard> cards) {
    int size = cards.size();
    // 几个连号
    int n = size / 3;

    if (size % 3 == 0 && n > 1 && n < 7) {
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      Integer firstCardNum = cardNums.firstEntry().getElement();
      for (int i = 0; i < n; i++) {
        if (cardNums.count(firstCardNum + i) != 3 || cardNums.contains(DdzCard.XIAO_WANG.num)
            || cardNums.contains(DdzCard.FANG_KUAI_ER.num)) {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  /**
   * 飞机带单翅膀：三顺＋同数量的单套牌。例如：333444+69 . ，333444+66也是合法的，333444+36？？ 先判断开始的牌是否连续三顺 不是的话再判断结尾的牌是否连续三顺
   * 
   * @param cards .
   * @return .
   */
  private static boolean isFeiJiDaiDan(List<DdzCard> cards) {
    // 飞机数量(三顺的数量)
    int feiJiNum = cards.size() / 4;

    // 飞机带单翅膀牌数量至少是4的2倍
    if (cards.size() % 4 == 0 && feiJiNum > 1 && feiJiNum < 6) {
      TreeMultiset<Integer> cardNums = TreeMultiset.create();

      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      for (Integer cardNum : cardNums.elementSet()) {
        if (cardNums.count(cardNum) >= 3) {
          boolean isFeiJiDaiDan = true;
          for (int i = 1; i < feiJiNum; i++) {
            if (cardNums.count(cardNum + i) < 3 || cardNum + i == DdzCard.FANG_KUAI_ER.num) {
              isFeiJiDaiDan = false;
              break;
            }
          }

          if (isFeiJiDaiDan) {
            return true;
          } else {
            continue;
          }
        }
      }
    }

    return false;
  }

  /**
   * 飞机带双翅膀：三顺＋同数量的对套牌。例如：333444555+667799，333444555 . + 666677？？？不合法
   * 
   * @param cards .
   * @return .
   */
  private static boolean isFeiJiDaiDui(List<DdzCard> cards) {
    // 飞机数量(三顺的数量)
    int feiJiNum = cards.size() / 5;

    // 飞机带双翅膀牌数量至少是5的2倍
    if (cards.size() % 5 == 0 && feiJiNum > 1 && feiJiNum < 5) {
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      // 对子数量
      int duiZiNum = 0;

      // 是否是飞机，即连顺
      boolean isFeiJi = false;

      for (Integer cardNum : cardNums.elementSet()) {
        if (cardNums.count(cardNum) == 3) {
          if (isFeiJi) {
            continue;
          }

          isFeiJi = true;
          for (int i = 1; i < feiJiNum; i++) {
            if (cardNums.count(cardNum + i) < 3 || cardNum + i == DdzCard.FANG_KUAI_ER.num) {
              isFeiJi = false;
              break;
            }
          }

        } else if (cardNums.count(cardNum) % 2 == 0) {
          duiZiNum += cardNums.count(cardNum) / 2;
        } else {
          return false;
        }
      }

      return isFeiJi && duiZiNum == feiJiNum;
    }

    return false;
  }

  /**
   * 四带二张单牌：张牌＋任意两套张数相同的单牌。例如：5555＋3＋8,允许4带2个王 .
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSiDaiDan(List<DdzCard> cards) {
    if (cards.size() == 6) {
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      for (Entry<Integer> cardNumEtr : cardNums.entrySet()) {
        if (cardNumEtr.getCount() == 4) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * 四带二对：四张牌＋任意两套张数相同的对牌。例如：4444＋55＋77,4444 . + 5555或者4444+8888也合法
   * 
   * @param cards .
   * @return .
   */
  private static boolean isSiDaiDui(List<DdzCard> cards) {

    if (cards.size() == 8) {
      TreeMultiset<Integer> cardNums = TreeMultiset.create();
      for (DdzCard card : cards) {
        cardNums.add(card.num);
      }

      // 排序去重以后第一张数字出现的重复次数
      int firstCardNumCount = cardNums.firstEntry().getCount();
      // 排序去重以后最后一张数字出现的重复次数
      int lastCardNumcount = cardNums.lastEntry().getCount();

      if (cardNums.entrySet().size() == 3) {
        if (firstCardNumCount == 4 && lastCardNumcount == 2) {
          return true;
        } else if (firstCardNumCount == 2 && lastCardNumcount == 2) {
          return true;
        } else if (firstCardNumCount == 2 && lastCardNumcount == 4) {
          return true;
        }
      }

      if (cardNums.entrySet().size() == 2) {
        if (firstCardNumCount == 4 && lastCardNumcount == 4) {
          return true;
        }
      }

    }

    return false;
  }

}
