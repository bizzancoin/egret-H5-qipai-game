
package com.idealighter.game.core.service.log.struct.backpack;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 喇叭使用日志 .
 *
 */
@LogTable(type = TableType.MONTH)
public class HornUseLog extends PlayerLog {

  @Column(type = "varchar", size = 1024, remark = "喇叭内容")
  public final String content;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   */
  public HornUseLog(Player player, String content) {
    super(player);
    this.content = content;
  }

}
