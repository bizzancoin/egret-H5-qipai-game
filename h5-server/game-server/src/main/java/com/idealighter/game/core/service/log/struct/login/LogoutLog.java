package com.idealighter.game.core.service.log.struct.login;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 登出日志 .
 * 
 * @date 2015年8月31日 下午9:05:15
 *
 */
@LogTable(type = TableType.DAY)
public class LogoutLog extends PlayerLog {

  @Column(type = "varchar", size = 62, remark = "登出ip")
  public final String ip;
  @Column(type = "bigint", size = 20, remark = "时长(秒)")
  public final long duration;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param duration .
   */
  public LogoutLog(Player player, long duration) {
    super(player);
    this.ip = player.ip;
    this.duration = duration;
  }
}
