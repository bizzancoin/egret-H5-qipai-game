
package com.idealighter.game.core.service.log.struct.shop;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;

/**
 * 购买道具日志.
 * 
 * @date 2015年11月16日 下午4:05:10
 *
 */
public class BuyItemLog extends PlayerLog {

  @Column(type = "varchar", size = 20, remark = "道具名称")
  public final String itemName;
  @Column(type = "int", size = 11, remark = "道具数量")
  public final int itemNum;
  @Column(type = "int", size = 11, remark = "单价")
  public final int price;
  @Column(type = "int", size = 11, remark = "单个道具赠送金币")
  public final int giveGold;
  @Column(type = "int", size = 11, remark = "合计道具赠送金币")
  public final int totalGiveGold;
  @Column(type = "int", size = 11, remark = "花费类型(1:元宝,2:金币)")
  public final int costType;
  @Column(type = "int", size = 11, remark = "花费")
  public final int cost;
  @Column(type = "bigint", size = 20, remark = "购买前元宝")
  public final long beforeIngot;
  @Column(type = "bigint", size = 20, remark = "购买后元宝")
  public final long afterIngot;
  @Column(type = "bigint", size = 20, remark = "购买前金币")
  public final long beforeGold;
  @Column(type = "bigint", size = 20, remark = "购买后金币")
  public final long afterGold;

  /**
   * 购买道具日志.
   * 
   * @param player 玩家.
   * @param itemName .
   * @param itemNum .
   * @param price .
   * @param giveGold .
   * @param totalGiveGold .
   * @param costType .
   * @param cost .
   * @param beforeIngot .
   * @param afterIngot .
   * @param beforeGold .
   * @param afterGold .
   */
  public BuyItemLog(Player player, String itemName, int itemNum, int price, int giveGold,
      int totalGiveGold, int costType, int cost, long beforeIngot, long afterIngot, long beforeGold,
      long afterGold) {
    super(player);
    this.itemName = itemName;
    this.itemNum = itemNum;
    this.price = price;
    this.giveGold = giveGold;
    this.totalGiveGold = totalGiveGold;
    this.costType = costType;
    this.cost = cost;
    this.beforeIngot = beforeIngot;
    this.afterIngot = afterIngot;
    this.beforeGold = beforeGold;
    this.afterGold = afterGold;
  }


}
