
package com.idealighter.game.core.service.log.struct.welfare;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 注册赠送金币日志 .
 * 
 * @date 2015年12月28日 下午12:02:44
 *
 */
@LogTable(type = TableType.DAY)
public class RegisterGiftLog extends PlayerLog {

  @Column(type = "bigint", size = 20, remark = "赠送前")
  public final long before;
  @Column(type = "bigint", size = 20, remark = "赠送后")
  public final long after;
  @Column(type = "bigint", size = 20, remark = "赠送的金币")
  public final long change;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param before .
   */
  public RegisterGiftLog(Player player, long before) {
    super(player);
    this.before = before;
    this.after = player.getGold();
    this.change = after - before;
  }

}
