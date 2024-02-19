
package com.idealighter.game.core.service.log.struct.personalcenter;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 实名认证日志 .
 * 
 * @date 2015年12月28日 上午10:48:50
 *
 */
@LogTable(type = TableType.SINGLE)
public class RealNameAuthenticateLog extends PlayerLog {

  @Column(type = "varchar", size = 12, remark = "姓名")
  public final String name;
  @Column(type = "varchar", size = 24, remark = "身份证号码")
  public final String idNo;

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   * @param name .
   * @param idNo .
   */
  public RealNameAuthenticateLog(Player player, String name, String idNo) {
    super(player);
    this.name = name;
    this.idNo = idNo;
  }

}
