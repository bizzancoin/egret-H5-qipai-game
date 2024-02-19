package com.idealighter.game.core.service.log.struct.bank;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 存/取钱日志 .
 * 
 * @date 2015年12月28日 上午11:30:16
 *
 */
@LogTable(type = TableType.MONTH)
public class DepositeWithdrawGoldLog extends PlayerLog {

  @Column(type = "tinyint", size = 4, remark = "绑定手机类型(0:存款,1:取款)")
  public final short type;
  @Column(type = "bigint", size = 20, remark = "改变前身上金币")
  public final long beforeGold;
  @Column(type = "bigint", size = 20, remark = "改变前保险箱金币")
  public final long beforeSafeGold;
  @Column(type = "bigint", size = 20, remark = "改变后身上金币")
  public final long afterGold;
  @Column(type = "bigint", size = 20, remark = "改变后保险箱金币")
  public final long afterSafeGold;
  @Column(type = "bigint", size = 20, remark = "改变的玩家身上金币(正：增加，负：减少)")
  public final long changeGold;
  @Column(type = "bigint", size = 20, remark = "改变的玩家保险箱金币(正：增加，负：减少)")
  public final long changeSafeGold;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param type . 绑定手机类型(0:存款,1:取款) .
   * @param beforeGold .
   * @param beforeSafeGold .
   * @param afterGold .
   * @param afterSafeGold .
   */
  public DepositeWithdrawGoldLog(Player player, int type, long beforeGold, long beforeSafeGold,
      long afterGold, long afterSafeGold) {
    super(player);
    this.type = (short) type;
    this.beforeGold = beforeGold;
    this.beforeSafeGold = beforeSafeGold;
    this.afterGold = afterGold;
    this.afterSafeGold = afterSafeGold;
    this.changeGold = afterGold - beforeGold;
    this.changeSafeGold = afterSafeGold - beforeSafeGold;
  }

}
