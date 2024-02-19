
package com.idealighter.game.core.service.games.common;

/**
 * 玩家出的牌的类型枚举 .
 * 
 * @date 2015年8月11日 上午11:02:15
 *
 */
public enum CardsType {

  DAN(1), // 单牌：单张牌
  DUI_ZI(2), // 对牌：两个
  SAN_BU_DAI(3), // 三张牌：三张牌点相同的牌。
  SAN_DAI_DAN(4), // 三带单：三张牌 ＋ 一张单牌。例如： 888+9
  SAN_DAI_DUI(5), // 三带对：三张牌 ＋ 一对牌。例如： 888+99
  SHUN_ZI(6), // 单顺：五张或更多连续的单牌。例如：3+4+5+6+7+8。不包括2和大、小王。
  SHUANG_SHUN(7), // 双顺：三个或更多连续的对牌。例如：33+44+55。不包括2和大、小王。
  SAN_SHUN(8), // 三顺：二个或更 多连续的三张牌。例如：333444、444555666777。不包括2和大、小王。
  FEI_JI_DAI_DAN(9), // 三顺＋同数量的一套牌,333444+69,69也可以是一对
  FEI_JI_DAI_DUI(10), // 三顺＋同数量的一套牌,333444555+667799
  ZHA_DAN(11), // 四张牌点相同的牌（如四个8）。除火箭和比自己大的炸弹外，什么牌型都可打。
  WANG_ZHA(12), // 火箭：即双王（双花牌），什么牌型都可打，是最大的牌
  SI_DAI_DAN(13), // 四张牌＋任意两套张数相同的单牌。例如：5555＋3＋8
  SI_DAI_DUI(14); // 四张牌＋任意两套张数相同的对牌。例如：4444＋55＋77

  public final int type;

  private CardsType(int type) {
    this.type = type;
  }

}
