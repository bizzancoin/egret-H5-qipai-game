
package com.idealighter.game.core.service.games.common;

/**
 * 房间状态 .
 * 
 * @date 2015年12月3日 下午8:38:06
 *
 */
public enum RoomState {
  IDLE("空闲"), GENERAL("普通"), CROWD("拥挤"), FULL("爆满");

  public final String desc;

  /**
   * 构造函数 .
   * 
   * @param desc .
   */
  private RoomState(String desc) {
    this.desc = desc;
  }

}
