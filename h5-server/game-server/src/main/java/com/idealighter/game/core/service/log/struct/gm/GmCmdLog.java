
package com.idealighter.game.core.service.log.struct.gm;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 玩家gm命令log .
 * 
 * @date 2015年9月14日 下午4:03:48
 *
 */
@LogTable(type = TableType.SINGLE)
public class GmCmdLog extends PlayerLog {

  @Column(type = "varchar", size = 1024, remark = "命令(包括参数)")
  public final String cmd;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param cmd .
   */
  public GmCmdLog(Player player, String cmd) {
    super(player);
    this.cmd = cmd;
  }

}
