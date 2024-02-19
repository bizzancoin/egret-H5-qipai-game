package com.idealighter.game.core.service.log.struct.personalcenter;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 修改银行密码日志 .
 * 
 * @date 2015年12月28日 上午10:54:32
 *
 */
@LogTable(type = TableType.MONTH)
public class ModifyBankPwdLog extends PlayerLog {

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public ModifyBankPwdLog(Player player) {
    super(player);
  }

}
