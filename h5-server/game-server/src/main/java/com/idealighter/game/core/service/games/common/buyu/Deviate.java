package com.idealighter.game.core.service.games.common.buyu;

/**
 * 偏差.
 * 
 * @date 2016年3月8日 下午4:14:48
 *
 */
public class Deviate {
  // 最小值
  private int min;
  // 最大值
  private int max;

  public Deviate() {}

  /**
   * 构造函数.
   * 
   * @param min .
   * @param max .
   */
  public Deviate(int min, int max) {
    super();
    this.min = min;
    this.max = max;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }


}
