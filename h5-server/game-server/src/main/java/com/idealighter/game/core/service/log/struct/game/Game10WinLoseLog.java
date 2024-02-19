
package com.idealighter.game.core.service.log.struct.game;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 飞禽走兽输赢日志 .
 * 
 * @date 2015年12月22日 上午11:01:14
 *
 */
@LogTable(type = TableType.DAY)
public class Game10WinLoseLog extends GameWinLoseLog {
  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   * @param chips .
   * @param gold .
   * @param reason .
   */
  public Game10WinLoseLog(Player player, AbstractSeat seat, long chips, long gold,
      LogReason reason) {
    super(player, seat, chips, gold, reason);
  }

  /**
   * 构造函数.
   * 
   * @param domain .
   * @param seat 座位信息.
   * @param chips .
   * @param gold .
   * @param reason .
   */
  public Game10WinLoseLog(PlayerBo domain, AbstractSeat seat, long chips, long gold,
      LogReason reason) {
    super(domain, seat, chips, gold, reason);
  }

}
