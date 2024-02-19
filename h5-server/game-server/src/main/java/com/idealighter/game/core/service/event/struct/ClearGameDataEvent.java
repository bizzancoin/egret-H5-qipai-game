package com.idealighter.game.core.service.event.struct;

/**
 * 清除游戏数据事件.
 * 
 * @date 2016年3月31日 下午4:22:59
 *
 */
public class ClearGameDataEvent {
  // 玩家id
  public final long playerId;

  /**
   * 构造函数.
   * 
   * @param playerId 玩家id.
   */
  public ClearGameDataEvent(long playerId) {
    this.playerId = playerId;
  }
}
