
package com.idealighter.game.core.service.log.struct.game;

import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 退出桌子日志.
 * 
 * @date 2015年12月28日 上午11:55:26
 *
 */
@LogTable(type = TableType.DAY)
public class ExitTableLog extends PlayerLog {

  @Column(type = "int", size = 11, remark = "游戏")
  public final int game;
  @Column(type = "varchar", size = 24, remark = "游戏名称")
  public final String gameName;
  @Column(type = "int", size = 11, remark = "房间")
  public final int room;
  @Column(type = "int", size = 11, remark = "桌子")
  public final int table;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param table .
   */
  public ExitTableLog(Player player, AbstractTable table) {
    super(player);
    this.game = table.room().game().getType();
    this.gameName = table.room().game().getDesc();
    this.room = table.room().getId();
    this.table = table.getId();
  }

  /**
   * 构造函数.
   * 
   * @param playerDom .
   * @param table .
   */
  public ExitTableLog(PlayerBo playerDom, AbstractTable table) {
    super(playerDom);
    this.game = table.room().game().getType();
    this.gameName = table.room().game().getDesc();
    this.room = table.room().getId();
    this.table = table.getId();
  }

}
