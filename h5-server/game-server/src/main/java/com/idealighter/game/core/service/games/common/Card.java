package com.idealighter.game.core.service.games.common;

/**
 * 0-12：方块，13-25：梅花，26-38：红桃，39-51：黑桃，52：小王，53：大王 .
 * 
 * @date 2015年8月5日 下午11:11:42
 *
 */
public enum Card {

  /*
   * 方块 . A - K,0-12
   */
  FANG_KUAI_A(14, CardType.FANG_KUAI), FANG_KUAI_ER(15, CardType.FANG_KUAI), FANG_KUAI_SAN(3,
      CardType.FANG_KUAI), FANG_KUAI_SI(4, CardType.FANG_KUAI), FANG_KUAI_WU(5,
          CardType.FANG_KUAI), FANG_KUAI_LIU(6, CardType.FANG_KUAI), FANG_KUAI_QI(7,
              CardType.FANG_KUAI), FANG_KUAI_BA(8, CardType.FANG_KUAI), FANG_KUAI_JIU(9,
                  CardType.FANG_KUAI), FANG_KUAI_SHI(10, CardType.FANG_KUAI), FANG_KUAI_J(11,
                      CardType.FANG_KUAI), FANG_KUAI_Q(12,
                          CardType.FANG_KUAI), FANG_KUAI_K(13, CardType.FANG_KUAI),

  /*
   * 梅花 . A - K,13-25
   */
  MEI_HUA_A(14, CardType.MEI_HUA), MEI_HUA_ER(15, CardType.MEI_HUA), MEI_HUA_SAN(3,
      CardType.MEI_HUA), MEI_HUA_SI(4, CardType.MEI_HUA), MEI_HUA_WU(5,
          CardType.MEI_HUA), MEI_HUA_LIU(6, CardType.MEI_HUA), MEI_HUA_QI(7,
              CardType.MEI_HUA), MEI_HUA_BA(8, CardType.MEI_HUA), MEI_HUA_JIU(9,
                  CardType.MEI_HUA), MEI_HUA_SHI(10, CardType.MEI_HUA), MEI_HUA_J(11,
                      CardType.MEI_HUA), MEI_HUA_Q(12,
                          CardType.MEI_HUA), MEI_HUA_K(13, CardType.MEI_HUA),

  /*
   * 红桃 . A - K，26-38
   */
  HONG_TAO_A(14, CardType.HONG_TAO), HONG_TAO_ER(15, CardType.HONG_TAO), HONG_TAO_SAN(3,
      CardType.HONG_TAO), HONG_TAO_SI(4, CardType.HONG_TAO), HONG_TAO_WU(5,
          CardType.HONG_TAO), HONG_TAO_LIU(6, CardType.HONG_TAO), HONG_TAO_QI(7,
              CardType.HONG_TAO), HONG_TAO_BA(8, CardType.HONG_TAO), HONG_TAO_JIU(9,
                  CardType.HONG_TAO), HONG_TAO_SHI(10, CardType.HONG_TAO), HONG_TAO_J(11,
                      CardType.HONG_TAO), HONG_TAO_Q(12,
                          CardType.HONG_TAO), HONG_TAO_K(13, CardType.HONG_TAO),

  // 黑桃 A - K,39-51
  HEI_TAO_A(14, CardType.HEI_TAO), HEI_TAO_ER(15, CardType.HEI_TAO), HEI_TAO_SAN(3,
      CardType.HEI_TAO), HEI_TAO_SI(4, CardType.HEI_TAO), HEI_TAO_WU(5,
          CardType.HEI_TAO), HEI_TAO_LIU(6, CardType.HEI_TAO), HEI_TAO_QI(7,
              CardType.HEI_TAO), HEI_TAO_BA(8, CardType.HEI_TAO), HEI_TAO_JIU(9,
                  CardType.HEI_TAO), HEI_TAO_SHI(10, CardType.HEI_TAO), HEI_TAO_J(11,
                      CardType.HEI_TAO), HEI_TAO_Q(12,
                          CardType.HEI_TAO), HEI_TAO_K(13, CardType.HEI_TAO),

  /*
   * 小王,大王 . 52,53
   */
  XIAO_WANG(16, CardType.XIAO_WANG), DA_WANG(17, CardType.DA_WANG);


  /**
   * 3-k(3-13),A:14,2:15,王:16 .
   */
  public final int num;
  /**
   * 牌的类型(0-5) . 0:方块,1:梅花,2:红桃,3:黑桃,4:小王,5:大王 .
   */
  public final CardType type;

  private Card(int num, CardType type) {
    this.num = num;
    this.type = type;
  }

  /**
   * 根据牌的id获取牌 .
   * 
   * @param cardId . 0-53
   * @return
   */
  public static Card card(int cardId) {
    if (cardId < 0 || cardId >= values().length) {
      throw new IllegalArgumentException("carId必须在[0,53]范围内");
    }

    return values()[cardId];
  }

}
