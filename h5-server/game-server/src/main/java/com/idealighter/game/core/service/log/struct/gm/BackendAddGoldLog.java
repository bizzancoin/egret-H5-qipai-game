package com.idealighter.game.core.service.log.struct.gm;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.TableType;

/**
 * 后台增加金币日志.
 *
 */
@LogTable(type = TableType.SINGLE)
public class BackendAddGoldLog extends DbLog {
  @Column(type = "bigint", size = 20, remark = "后台用户id")
  public final int adminId;
  @Column(type = "varchar", size = 128, remark = "后台用户名称")
  public final String adminName;
  @Column(type = "bigint", size = 20, remark = "玩家id")
  public final long playerId;
  @Column(type = "bigint", size = 20, remark = "玩家id")
  public final long superId;
  @Column(type = "varchar", size = 45, remark = "玩家名称")
  public final String playerName;
  @Column(type = "bigint", size = 20, remark = "金币")
  public final long gold;

  /**
   * 构造函数.
   * 
   * @param adminId 用户id.
   * @param adminName 用户名.
   * @param playerId 玩家id.
   * @param playerName 玩家昵称.
   * @param gold 金币.
   */
  public BackendAddGoldLog(int adminId, String adminName, long playerId, long superId,
      String playerName, long gold) {
    super();
    this.adminId = adminId;
    this.adminName = adminName;
    this.playerId = playerId;
    this.superId = superId;
    this.playerName = playerName;
    this.gold = gold;
  }

}
