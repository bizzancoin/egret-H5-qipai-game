
package com.idealighter.game.core.service.log.struct.welfare;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;


/**
 * 分享日志.
 *
 */
@LogTable(type = TableType.DAY)
public class ShareLog extends PlayerLog {

  @Column(type = "bigint", size = 20, remark = "签到前")
  public final long before;
  @Column(type = "bigint", size = 20, remark = "签到后")
  public final long after;
  @Column(type = "bigint", size = 20, remark = "赠送的金币")
  public final long change;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param before .
   */
  public ShareLog(Player player, long before) {
    super(player);
    this.before = before;
    this.after = player.getGold();
    this.change = after - before;
  }

}
