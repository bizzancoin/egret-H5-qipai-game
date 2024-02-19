package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 游戏清理玩家，并推送玩家退出.
 *
 */
public class GameClearExitEvent {

  public final Player player;
  public final String gameName;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   */
  public GameClearExitEvent(Player player, String gameName) {
    this.player = player;
    this.gameName = gameName;
  }

}
