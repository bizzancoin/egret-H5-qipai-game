package com.idealighter.game.games.common;

/**
 * 牌的类型.
 *
 */
public enum NiuNiuCardsType {

  MEI_NIU(0, 1), // 没牛
  NIU_1(1, 1), // 牛1
  NIU_2(2, 1), // 牛2
  NIU_3(3, 1), // 牛3
  NIU_4(4, 1), // 牛4
  NIU_5(5, 1), // 牛5
  NIU_6(6, 1), // 牛6
  NIU_7(7, 2), // 牛7
  NIU_8(8, 3), // 牛8
  NIU_9(9, 4), // 牛9
  NIU_NIU(10, 5), // 牛牛
  SI_HUA(11, 7), // 四花
  SI_ZHA(12, 8), // 四炸
  WU_HUA(13, 9), // 五花
  WU_XIAO(14, 10); // 五小

  public final int niu;
  // 倍数
  public final int multiple;

  /**
   * 构造函数.
   * 
   * @param niu .
   */
  private NiuNiuCardsType(int niu, int multiple) {
    this.niu = niu;
    this.multiple = multiple;
  }

  /**
   * 获取牌型.
   * 
   * @param niu .
   * @return .
   */
  public static NiuNiuCardsType geCardsType(int niu) {
    for (NiuNiuCardsType cardsType : NiuNiuCardsType.values()) {
      if (cardsType.niu == niu) {
        return cardsType;
      }
    }
    return null;
  }

  /**
   * 获取最大赔率.
   * 
   * @Title getMaxMultiple.
   * @author houdongsheng
   * @date 2018年3月7日 下午5:31:01
   * @return int 最大赔率
   */
  public static int getMaxMultiple() {
    int result = 0;
    for (NiuNiuCardsType cardsType : NiuNiuCardsType.values()) {
      if (cardsType.multiple > result) {
        result = cardsType.multiple;
      }
    }
    return result;
  }
}
