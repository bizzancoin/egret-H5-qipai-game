package com.idealighter.game.core.service.gamehall.event;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.player.struct.Player;

/**
 * 退出桌子事件.
 *
 */
public class ExitTableEvent {

  public final Player player;
  public final AbstractSeat seat;


  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   */
  public ExitTableEvent(Player player, AbstractSeat seat) {
    this.player = player;
    this.seat = seat;
  }


}
