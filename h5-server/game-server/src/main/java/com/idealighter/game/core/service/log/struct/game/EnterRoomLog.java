package com.idealighter.game.core.service.log.struct.game;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 退出房间日志 .
 *
 */
@LogTable(type = TableType.DAY)
public class EnterRoomLog extends PlayerLog {

  @Column(type = "int", size = 11, remark = "游戏")
  public final int game;
  @Column(type = "varchar", size = 24, remark = "游戏名称")
  public final String gameName;
  @Column(type = "int", size = 11, remark = "房间")
  public final int room;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param room 房间.
   */
  public EnterRoomLog(Player player, AbstractRoom room) {
    super(player);
    this.game = room.game().getType();
    this.gameName = room.game().getDesc();
    this.room = room.getId();
  }

}
