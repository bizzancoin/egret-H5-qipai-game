
package com.idealighter.game.core.service.log.struct.resource;

import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 保险箱金币日志 .
 * 
 * @date 2015年11月12日 下午8:41:17
 *
 */
@LogTable(type = TableType.DAY)
public class SafeGoldLog extends PlayerLog {
  @Column(type = "bigint", size = 20, remark = "改变前")
  public final long before;
  @Column(type = "bigint", size = 20, remark = "改变后")
  public final long after;
  @Column(type = "bigint", size = 20, remark = "改变的值(正：增加，负：减少)")
  public final long change;
  @Column(type = "varchar", size = 30, remark = "订单号")
  public final String orderNo;
  @Column(type = "varchar", size = 64, remark = "改变的原因")
  public final String reason;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param before .
   * @param after .
   * @param reason .
   */
  public SafeGoldLog(Player player, long before, long after, String orderNo, LogReason reason) {
    super(player);
    this.before = before;
    this.after = after;
    this.change = after - before;
    this.reason = "" + reason;
    this.orderNo = orderNo;
  }

  /**
   * 构造函数 .
   * 
   * @param domain .
   * @param before .
   * @param after .
   * @param reason .
   */
  public SafeGoldLog(PlayerBo domain, long before, long after, String orderNo, LogReason reason) {
    super(domain);
    this.before = before;
    this.after = after;
    this.change = after - before;
    this.reason = "" + reason;
    this.orderNo = orderNo;
  }
}
