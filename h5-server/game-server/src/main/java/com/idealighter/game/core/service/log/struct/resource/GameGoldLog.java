package com.idealighter.game.core.service.log.struct.resource;

import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

@LogTable(type = TableType.DAY)
public class GameGoldLog extends PlayerLog {

  @Column(type = "bigint", size = 20, remark = "投注金额")
  public final long betting;
  @Column(type = "bigint", size = 20, remark = "中奖金额")
  public final long bonus;
  @Column(type = "bigint", size = 20, remark = "平台盈亏")
  public final long profit;
  @Column(type = "int", size = 11, remark = "游戏")
  public final int type;
  @Column(type = "int", size = 11, remark = "玩家类型")
  public final int playerType; // 1 闲家 2庄家

  @Column(type = "varchar", size = 64, remark = "改变的原因")
  public final String reason;


  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param reason 原因.
   * @param betting 下注金额.
   * @param bonus 中奖金额.
   * @param gameType 1.百人牛牛 2.百家乐3.金蟾捕鱼
   */
  public GameGoldLog(Player player, LogReason reason, long betting, long bonus, int gameType,
      int playerType) {
    super(player);

    this.betting = betting;
    this.type = gameType;
    this.bonus = bonus;
    this.profit = betting - bonus;
    this.playerType = playerType;

    this.reason = "" + reason;
  }

  /**
   * 构造函数.
   * 
   * @param playerBo 玩家.
   * @param reason 原因.
   * @param betting 下注金额.
   * @param bonus 中奖金额.
   * @param gameType 1.百人牛牛 2.百家乐3.金蟾捕鱼
   */
  public GameGoldLog(PlayerBo playerBo, LogReason reason, long betting, long bonus, int gameType,
      int playerType) {
    super(playerBo);

    this.betting = betting;
    this.type = gameType;
    this.bonus = bonus;
    this.profit = betting - bonus;
    this.playerType = playerType;

    this.reason = "" + reason;
  }
}
