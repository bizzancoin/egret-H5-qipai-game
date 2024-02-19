package com.idealighter.game.core.service.log.struct.personalcenter;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 绑定手机日志 .
 * 
 * @date 2015年12月28日 上午11:00:39
 *
 */
@LogTable(type = TableType.MONTH)
public class BindPhoneLog extends PlayerLog {

  @Column(type = "tinyint", size = 4, remark = "绑定手机类型(0:解绑手机,1:绑定手机)")
  public final short type;

  /**
   * 绑定手机日志.
   * 
   * @param player 玩家.
   * @param type .
   */
  public BindPhoneLog(Player player, int type) {
    super(player);
    this.type = (short) type;
  }

}
