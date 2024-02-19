
package com.idealighter.game.core.service.games.common;

/**
 * 座位状态 .
 * 
 * @date 2015年11月17日 下午6:47:37
 *
 */
public enum SeatState {
  // 1:入座,2:准备,3:游戏中
  SEATED(1), READY(2), GAMING(3),;

  public int val;

  /**
   * 构造函数 .
   * 
   * @param val .
   */
  private SeatState(int val) {
    this.val = val;
  }

}
