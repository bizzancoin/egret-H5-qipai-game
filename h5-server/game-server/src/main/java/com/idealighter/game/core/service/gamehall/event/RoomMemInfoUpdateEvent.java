
package com.idealighter.game.core.service.gamehall.event;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 房间成员信息更新事件 .
 */
public class RoomMemInfoUpdateEvent {

  // 信息变化的玩家
  public final Player player;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public RoomMemInfoUpdateEvent(Player player) {
    this.player = player;
  }
}
