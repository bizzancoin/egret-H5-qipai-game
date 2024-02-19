
package com.idealighter.game.core.service.games.baccarat.struct;

import com.idealighter.game.core.service.games.common.CardSuit;

/**
 * 0-12：方块，13-25：梅花，26-38：红桃，39-51：黑桃 .
 */
public enum BaccaratCard {

  /*
   * 方块 . A - K,0-12
   */
  FANG_KUAI_A(1, 1, CardSuit.FANG_KUAI), FANG_KUAI_ER(2, 2, CardSuit.FANG_KUAI), FANG_KUAI_SAN(3, 3,
      CardSuit.FANG_KUAI), FANG_KUAI_SI(4, 4, CardSuit.FANG_KUAI), FANG_KUAI_WU(5, 5,
          CardSuit.FANG_KUAI), FANG_KUAI_LIU(6, 6, CardSuit.FANG_KUAI), FANG_KUAI_QI(7, 7,
              CardSuit.FANG_KUAI), FANG_KUAI_BA(8, 8, CardSuit.FANG_KUAI), FANG_KUAI_JIU(9, 9,
                  CardSuit.FANG_KUAI), FANG_KUAI_SHI(10, 10, CardSuit.FANG_KUAI), FANG_KUAI_J(11,
                      10, CardSuit.FANG_KUAI), FANG_KUAI_Q(12, 10,
                          CardSuit.FANG_KUAI), FANG_KUAI_K(13, 10, CardSuit.FANG_KUAI),

  /*
   * 梅花 . A - K,13-25
   */
  MEI_HUA_A(1, 1, CardSuit.MEI_HUA), MEI_HUA_ER(2, 2, CardSuit.MEI_HUA), MEI_HUA_SAN(3, 3,
      CardSuit.MEI_HUA), MEI_HUA_SI(4, 4, CardSuit.MEI_HUA), MEI_HUA_WU(5, 5,
          CardSuit.MEI_HUA), MEI_HUA_LIU(6, 6, CardSuit.MEI_HUA), MEI_HUA_QI(7, 7,
              CardSuit.MEI_HUA), MEI_HUA_BA(8, 8, CardSuit.MEI_HUA), MEI_HUA_JIU(9, 9,
                  CardSuit.MEI_HUA), MEI_HUA_SHI(10, 10, CardSuit.MEI_HUA), MEI_HUA_J(11, 10,
                      CardSuit.MEI_HUA), MEI_HUA_Q(12, 10,
                          CardSuit.MEI_HUA), MEI_HUA_K(13, 10, CardSuit.MEI_HUA),

  /*
   * 红桃 . A - K，26-38
   */
  HONG_TAO_A(1, 1, CardSuit.HONG_TAO), HONG_TAO_ER(2, 2, CardSuit.HONG_TAO), HONG_TAO_SAN(3, 3,
      CardSuit.HONG_TAO), HONG_TAO_SI(4, 4, CardSuit.HONG_TAO), HONG_TAO_WU(5, 5,
          CardSuit.HONG_TAO), HONG_TAO_LIU(6, 6, CardSuit.HONG_TAO), HONG_TAO_QI(7, 7,
              CardSuit.HONG_TAO), HONG_TAO_BA(8, 8, CardSuit.HONG_TAO), HONG_TAO_JIU(9, 9,
                  CardSuit.HONG_TAO), HONG_TAO_SHI(10, 10, CardSuit.HONG_TAO), HONG_TAO_J(11, 10,
                      CardSuit.HONG_TAO), HONG_TAO_Q(12, 10,
                          CardSuit.HONG_TAO), HONG_TAO_K(13, 10, CardSuit.HONG_TAO),

  // 黑桃 A - K,39-51
  HEI_TAO_A(1, 1, CardSuit.HEI_TAO), HEI_TAO_ER(2, 2, CardSuit.HEI_TAO), HEI_TAO_SAN(3, 3,
      CardSuit.HEI_TAO), HEI_TAO_SI(4, 4, CardSuit.HEI_TAO), HEI_TAO_WU(5, 5,
          CardSuit.HEI_TAO), HEI_TAO_LIU(6, 6, CardSuit.HEI_TAO), HEI_TAO_QI(7, 7,
              CardSuit.HEI_TAO), HEI_TAO_BA(8, 8, CardSuit.HEI_TAO), HEI_TAO_JIU(9, 9,
                  CardSuit.HEI_TAO), HEI_TAO_SHI(10, 10, CardSuit.HEI_TAO), HEI_TAO_J(11, 10,
                      CardSuit.HEI_TAO), HEI_TAO_Q(12, 10,
                          CardSuit.HEI_TAO), HEI_TAO_K(13, 10, CardSuit.HEI_TAO);

  /**
   * 3-k(3-13),A:14,2:15,王:16 .
   */
  public final int num;

  public final int power;
  /**
   * 牌的类型(0-5) . 0:方块,1:梅花,2:红桃,3:黑桃 .
   */
  public final CardSuit type;

  private BaccaratCard(int power, int num, CardSuit type) {
    this.power = power;
    this.num = num;
    this.type = type;
  }

  /**
   * 根据牌的id获取牌 .
   * 
   * @param cardId . 0-53
   * @return
   */
  public static BaccaratCard card(int cardId) {
    if (cardId < 0 || cardId >= values().length) {
      throw new IllegalArgumentException("carId必须在[0,53]范围内");
    }

    return values()[cardId];
  }

  /**
   * 同一张牌判断 .
   * 
   * 
   * @param obj1 .
   * @param obj2 .
   * @return
   */
  public static boolean equal(Object obj1, Object obj2) {
    if (!(obj1 instanceof BaccaratCard) || !(obj2 instanceof BaccaratCard)) {
      return false;
    }

    BaccaratCard other1 = (BaccaratCard) obj1;
    BaccaratCard other2 = (BaccaratCard) obj2;

    return (other1.num == other2.num) && (other1.type == other2.type);

  }

}
