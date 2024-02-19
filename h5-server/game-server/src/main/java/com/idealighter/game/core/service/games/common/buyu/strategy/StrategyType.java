
package com.idealighter.game.core.service.games.common.buyu.strategy;

/**
 * 刷鱼策略类型 .
 * 
 * @date 2016年3月10日 下午4:35:11
 *
 */
public enum StrategyType {
  GENERAL(1, "普通"), SHOAL(2, "鱼阵"), POINT(3, "点"), LINE(4, "线"), CIRCLE(5, "圆"), ROW(6,
      "鱼排"), SPREAD(7, "扩散出圈鱼"),;

  public final int val;
  public final String desc;

  /**
   * 构造函数 .
   * 
   * @param val .
   * @param desc .
   */
  private StrategyType(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }

  /**
   * 根据type获取StrategyType .
   * 
   * @param type .
   * @return
   */
  public static StrategyType get(int type) {
    for (StrategyType t : values()) {
      if (t.val == type) {
        return t;
      }
    }

    return null;
  }
}
