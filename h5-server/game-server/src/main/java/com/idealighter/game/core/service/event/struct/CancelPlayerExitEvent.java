package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 取消游戏清理玩家.
 *
 */
public class CancelPlayerExitEvent {

  public final Player player;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   */
  public CancelPlayerExitEvent(Player player) {
    this.player = player;
  }

}
