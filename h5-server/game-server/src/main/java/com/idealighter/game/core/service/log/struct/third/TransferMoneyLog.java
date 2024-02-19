package com.idealighter.game.core.service.log.struct.third;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogIndexKey;
import com.idealighter.game.dblog.annotation.LogIndexKeyField;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.IndexType;
import com.idealighter.game.dblog.core.TableType;

@LogTable(type = TableType.MONTH)
@LogIndexKey(type = IndexType.NORMAY, keyName = "idx_order_no",
    fields = { @LogIndexKeyField(field = "orderNo", order = 1) })
public class TransferMoneyLog extends PlayerLog {

  @Column(type = "varchar", size = 64, remark = "订单号")
  public final String orderNo;

  @Column(type = "bigint", size = 20, remark = "交易金额")
  public final Long price;

  @Column(type = "bigint", size = 20, remark = "交易前余额")
  public final Long beforeBalance;

  @Column(type = "bigint", size = 20, remark = "交易后余额")
  public final Long afterBalance;
  
  @Column(type = "bigint", size = 20, remark = "交易前游戏余额")
  public final Long beforeSafeGold;
  
  @Column(type = "bigint", size = 20, remark = "交易后游戏余额")
  public final Long afterSafeGold;

  @Column(type = "tinyint", size = 4, remark = "交易类型 0,：从棋牌提取，1：存入到棋牌")
  public final byte flag;

  /**
   * 转账记录.
   * 
   * @param player 玩家.
   * @param orderNo 订单号.
   * @param price 价格.
   * @param beforeBalance 交易前余额.
   * @param afterBalance 交易后余额.
   * @param flag 交易类型 0,：从棋牌提取，1：存入到棋牌.
   */
  public TransferMoneyLog(Player player, String orderNo, Long price, Long beforeBalance,
      Long afterBalance,Long beforeSafeGold,Long afterSafeGold, byte flag) {
    super(player);
    this.orderNo = orderNo;
    this.price = price;
    this.beforeBalance = beforeBalance;
    this.afterBalance = afterBalance;
    this.beforeSafeGold = beforeSafeGold;
    this.afterSafeGold = afterSafeGold;
    this.flag = flag;
  }

  /**
   * 转账记录.
   * 
   * @param playerBo 玩家dom.
   * @param orderNo 订单号.
   * @param price 交易金额.
   * @param beforeBalance 交易前余额.
   * @param afterBalance 交易后余额.
   * @param flag 交易类型 0,：从棋牌提取，1：存入到棋牌.
   */
  public TransferMoneyLog(PlayerBo playerBo, String orderNo, Long price, Long beforeBalance,
      Long afterBalance,Long beforeSafeGold,Long afterSafeGold, byte flag) {
    super(playerBo);
    this.orderNo = orderNo;
    this.price = price;
    this.beforeBalance = beforeBalance;
    this.afterBalance = afterBalance;
    this.beforeSafeGold = beforeSafeGold;
    this.afterSafeGold = afterSafeGold;
    this.flag = flag;
  }

}
