package com.idealighter.game.core.service.games.ddz.util;

import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import com.idealighter.game.core.service.games.ddz.struct.DdzCardsType;
import java.util.List;

/**
 * CardsType比较工具类 .
 * 
 * @date 2015年8月16日 下午1:06:57
 *
 */
public class CardsTypeComparator {

  private CardsTypeComparator() {}

  /**
   * 比较我的牌和另一个人牌的大小,如果不是王炸或者炸弹那么myCards数量必须和上一家preCards数量一致 .
   * 
   * @param mySortedCards . 牌按照数字排过序 .
   * @param myCardsType .
   * @param preSortedCards . 牌按照数字排过序 .
   * @param preCardsType .
   * @return -2:牌数量或牌型不匹配,-1：我小，0：相等，1：我大 .
   */
  public static int compare(List<DdzCard> mySortedCards, DdzCardsType myCardsType,
      List<DdzCard> preSortedCards, DdzCardsType preCardsType) {
    /*
     * 集中判断特殊情况 . 我是王炸 上家是王炸 上家不是炸弹，我是炸弹 剩下的牌型和数量必须一致
     */
    // 我是王炸
    if (myCardsType == DdzCardsType.WANG_ZHA) {
      return 1;
    }

    // 上家是王炸
    if (preCardsType == DdzCardsType.WANG_ZHA) {
      return -1;
    }

    // 上家不是炸弹，我是炸弹
    if (preCardsType != DdzCardsType.ZHA_DAN && myCardsType == DdzCardsType.ZHA_DAN) {
      return 1;
    }

    // 牌型和数量是否匹配
    if (mySortedCards.size() != preSortedCards.size() || myCardsType != preCardsType) {
      return -2;
    }

    /*
     * 判断同牌型的大小(除去WANG_ZHA) . DAN, //单牌：单张牌 DUI_ZI, //对牌：两个 SAN_BU_DAI, //三张牌：三张牌点相同的牌。 SAN_DAI_DAN,
     * //三带单：三张牌 . ＋ 一张单牌。例如： 888+9 SAN_DAI_DUI, //三带对：三张牌 ＋ 一对牌。例如： 888+99 SHUN_ZI,
     * //单顺：五张或更多连续的单牌。例如：3+4+5+6+7+8。不包括2和大、小王。 . SHUANG_SHUN, //双顺：三个或更多连续的对牌。
     * 例如：33+44+55。不包括2和大、小王。 SAN_SHUN, . //三顺：二个或更 多连续的三张牌。例如：333444、444555666777。不包括2和大、小王。
     * FEI_JI_DAI_DAN, //三顺＋同数量的一套牌,333444+69,69也可以是一对 . FEI_JI_DAI_DUI,
     * //三顺＋同数量的一套牌,333444555+667799 ZHA_DAN, //四张牌点相同的牌（如四个8）。除火箭和比自己大的炸弹外，什么牌型都可打。 . SI_DAI_DAN,
     * //四张牌＋任意两套张数相同的单牌。 例如：5555＋3＋8 SI_DAI_DUI; //四张牌＋任意两套张数相同的对牌。例如：4444＋55＋77 .
     */

    // 上家出DAN,DUI_ZI,SAN_BU_DAI,SHUN_ZI,SHUANG_SHUN,SAN_SHUN,ZHA_DAN，只需要比较第1张牌
    if (preCardsType == myCardsType && (preCardsType == DdzCardsType.DAN
        || preCardsType == DdzCardsType.DUI_ZI || preCardsType == DdzCardsType.SAN_BU_DAI
        || preCardsType == DdzCardsType.SHUN_ZI
        || preCardsType == DdzCardsType.SHUANG_SHUN
        || preCardsType == DdzCardsType.SAN_SHUN
        || preCardsType == DdzCardsType.ZHA_DAN)) {
      int myCardNum = mySortedCards.get(0).num;
      int preCardNum = preSortedCards.get(0).num;

      if (myCardNum > preCardNum) {
        return 1;
      } else if (myCardNum == preCardNum) {
        return 0;
      } else {
        return -1;
      }
    }

    // 上家出SAN_DAI_DAN,SAN_DAI_DUI,SI_DAI_DAN，只需要比较第3张牌
    if (preCardsType == DdzCardsType.SAN_DAI_DAN
        || preCardsType == DdzCardsType.SAN_DAI_DUI
        || preCardsType == DdzCardsType.SI_DAI_DAN) {
      int myCardNum = mySortedCards.get(2).num;
      int preCardNum = preSortedCards.get(2).num;

      if (myCardNum > preCardNum) {
        return 1;
      } else if (myCardNum == preCardNum) {
        return 0;
      } else {
        return -1;
      }
    }

    // 上家出SI_DAI_DUI,前面三张如果相等则取第一张来比较，否则取第5张
    if (preCardsType == DdzCardsType.SI_DAI_DUI) {
      int myCardNum;
      int preCardNum;

      if (mySortedCards.get(0).num == mySortedCards.get(1).num
          && preSortedCards.get(1).num == preSortedCards.get(2).num) {
        myCardNum = mySortedCards.get(0).num;
      } else {
        myCardNum = mySortedCards.get(4).num;
      }

      if (preSortedCards.get(0).num == preSortedCards.get(1).num
          && preSortedCards.get(1).num == preSortedCards.get(2).num) {
        preCardNum = preSortedCards.get(0).num;
      } else {
        preCardNum = preSortedCards.get(4).num;
      }

      if (myCardNum > preCardNum) {
        return 1;
      } else if (myCardNum == preCardNum) {
        return 0;
      } else {
        return -1;
      }
    }

    /*
     * 上家出FEI_JI_DAI_DAN . 8张时比较第3张 12张时比较第4张 16张时比较第5张 20张时比较第6张
     */
    if (preCardsType == DdzCardsType.FEI_JI_DAI_DAN) {
      int myCardNum = mySortedCards.get(mySortedCards.size() / 4).num;
      int preCardNum = preSortedCards.get(preSortedCards.size() / 4).num;

      if (myCardNum > preCardNum) {
        return 1;
      } else if (myCardNum == preCardNum) {
        return 0;
      } else {
        return -1;
      }
    }

    /*
     * 上家出FEI_JI_DAI_DUI . 10张时比较第5张 15张时比较第7张 20张时比较第9张
     */
    if (preCardsType == DdzCardsType.FEI_JI_DAI_DUI) {
      int myCardNum = mySortedCards.get(mySortedCards.size() / 5 * 2).num;
      int preCardNum = preSortedCards.get(preSortedCards.size() / 5 * 2).num;

      if (myCardNum > preCardNum) {
        return 1;
      } else if (myCardNum == preCardNum) {
        return 0;
      } else {
        return -1;
      }
    }

    return 0;
  }
}
