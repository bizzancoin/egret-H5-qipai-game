
package com.idealighter.game.core.service.log.struct.bank;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 转账日志 .
 * 
 * @date 2015年12月28日 上午11:30:16
 *
 */
@LogTable(type = TableType.SINGLE)
public class TransferGoldLog extends PlayerLog {

  @Column(type = "bigint", size = 20, remark = "转账的目标玩家靓号")
  public final long targetSuperId;

  @Column(type = "varchar", size = 32, remark = "转账的目标昵称")
  public final String targetPlayerName;

  @Column(type = "int", size = 11, remark = "转账的费率")
  public final int feeRate;

  @Column(type = "bigint", size = 20, remark = "转账手续费总值")
  public final long fee;

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
   * 构造函数 .
   * 
   * @param player 出钱的人.
   * @param targetSuperId 收钱的人.
   * @param targetPlayerName 收钱的昵称.
   * @param feeRate 费率.
   * @param fee 费用.
   * @param beforeGold 转账前金币.
   * @param beforeSafeGold 转账前银行中的金币.
   * @param afterGold 转账后金币.
   * @param afterSafeGold 转账后银行的金币.
   */
  public TransferGoldLog(Player player, long targetSuperId, String targetPlayerName, int feeRate,
      long fee, long beforeGold, long beforeSafeGold, long afterGold, long afterSafeGold) {
    super(player);
    this.targetSuperId = targetSuperId;
    this.targetPlayerName = targetPlayerName;
    this.feeRate = feeRate;
    this.fee = fee;
    this.beforeGold = beforeGold;
    this.beforeSafeGold = beforeSafeGold;
    this.afterGold = afterGold;
    this.afterSafeGold = afterSafeGold;
    this.changeGold = afterGold - beforeGold;
    this.changeSafeGold = afterSafeGold - beforeSafeGold;
  }

}
