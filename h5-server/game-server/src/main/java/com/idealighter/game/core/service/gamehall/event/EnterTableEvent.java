package com.idealighter.game.core.service.gamehall.event;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.player.struct.Player;

/**
 * 进入桌子事件 .
 * 
 * 
 * @date 2015年11月13日 下午6:10:09
 *
 */
public class EnterTableEvent {

  public final Player player;
  public final AbstractSeat seat;

  /**
   * 进入桌子事件.
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   */
  public EnterTableEvent(Player player, AbstractSeat seat) {
    this.player = player;
    this.seat = seat;
  }

}
