package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 玩家退出游戏事件 .
 * 
 * @date 2015年8月2日 上午12:21:45
 *
 */
public class PlayerExitEvent {

  public final Player player;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public PlayerExitEvent(Player player) {
    this.player = player;
  }

}
