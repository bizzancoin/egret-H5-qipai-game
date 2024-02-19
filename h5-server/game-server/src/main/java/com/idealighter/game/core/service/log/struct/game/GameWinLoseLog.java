
package com.idealighter.game.core.service.log.struct.game;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;

/**
 * 游戏输赢日志 .
 */
public abstract class GameWinLoseLog extends PlayerLog {

  @Column(type = "int", size = 11, remark = "游戏")
  public final int game;
  @Column(type = "int", size = 11, remark = "房间")
  public final int room;
  @Column(type = "int", size = 11, remark = "桌子")
  public final int table;
  @Column(type = "bigint", size = 20, remark = "输赢的筹码(正:赢,负:输)")
  public final long chips;
  @Column(type = "bigint", size = 20, remark = "输赢的筹码换算的金币(正:赢,负:输)")
  public final long gold;
  @Column(type = "varchar", size = 64, remark = "改变的原因")
  public final String reason;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   * @param chips .
   * @param gold .
   */
  public GameWinLoseLog(Player player, AbstractSeat seat, long chips, long gold, LogReason reason) {
    super(player);
    this.game = seat.table().room().game().getType();
    this.room = seat.table().room().getId();
    this.table = seat.table().getId();
    this.chips = chips;
    this.gold = gold;
    this.reason = "" + reason;
  }

  /**
   * 构造函数.
   * 
   * @param domain .
   * @param seat 座位信息.
   * @param chips .
   * @param gold .
   */
  public GameWinLoseLog(PlayerBo domain, AbstractSeat seat, long chips, long gold,
      LogReason reason) {
    super(domain);
    this.game = seat.table().room().game().getType();
    this.room = seat.table().room().getId();
    this.table = seat.table().getId();
    this.chips = chips;
    this.gold = gold;
    this.reason = "" + reason;
  }

}
