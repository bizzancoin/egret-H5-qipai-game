package com.idealighter.game.core.service.games.common.buyu;

/**
 * 曲线类型.
 *
 */
public enum CurveType {
  PARABOLA(1), // 抛物线[y = ax2 + bx + c]
  CUBICCVE(2), // 三次曲线[y = ax3]，
  EXPONENTIAL(3), // 指数函数[y = a(x)]
  LOGARITHM(4), // 对数函数[y = alog(x)]
  SIN(5), // 正弦曲线[y = asin(x)]
  COS(6), // 余弦曲线[y = acos(x)]
  CIRCLE(7), // 圆[(x + a)2 + (y + b)2 = r2]
  LINE(8), // 直线[y = kx]
  ;

  // 值
  public final int type;

  /**
   * 构造函数.
   * 
   * @param type .
   */
  private CurveType(int type) {
    this.type = type;
  }

  /**
   * 获取.
   * 
   * @param type .
   * @return
   */
  public static CurveType get(int type) {
    for (CurveType t : values()) {
      if (t.type == type) {
        return t;
      }
    }

    return null;
  }
}
