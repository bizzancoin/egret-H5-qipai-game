
package com.idealighter.game.core.service.gamehall.event;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.player.struct.Player;

/**
 * 退出房间事件.
 * 
 * @date 2015年11月13日 下午6:09:58
 *
 */
public class ExitRoomEvent {


  public final Player player;
  public final AbstractRoom room;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param room .
   */
  public ExitRoomEvent(Player player, AbstractRoom room) {
    this.player = player;
    this.room = room;
  }

}
