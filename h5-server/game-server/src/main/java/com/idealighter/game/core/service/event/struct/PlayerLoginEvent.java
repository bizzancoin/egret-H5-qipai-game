package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 玩家登录事件 .
 * 
 * @date 2015年9月2日 下午1:34:04
 *
 */
public class PlayerLoginEvent {

  public final Player player;

  public PlayerLoginEvent(Player player) {
    this.player = player;
  }

}
