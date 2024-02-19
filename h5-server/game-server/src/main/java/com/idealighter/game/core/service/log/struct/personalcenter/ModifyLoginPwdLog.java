package com.idealighter.game.core.service.log.struct.personalcenter;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 修改登录密码日志 .
 * 
 * @date 2015年12月28日 上午10:53:16
 *
 */
@LogTable(type = TableType.MONTH)
public class ModifyLoginPwdLog extends PlayerLog {

  /**
   * 构造函数 .
   * 
   * @param player 玩家.
   */
  public ModifyLoginPwdLog(Player player) {
    super(player);
  }

  public ModifyLoginPwdLog(PlayerBo playerBo) {
    super(playerBo);
  }

}
