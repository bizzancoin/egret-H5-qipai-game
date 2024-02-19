package com.idealighter.game.core.service.log.struct.personalcenter;

import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 需改密保日志 .
 * 
 * @date 2015年12月28日 上午10:57:06
 *
 */
@LogTable(type = TableType.SINGLE)
public class ModifyPwdProtectLog extends PlayerLog {

  public ModifyPwdProtectLog(Player player) {
    super(player);
  }


}
