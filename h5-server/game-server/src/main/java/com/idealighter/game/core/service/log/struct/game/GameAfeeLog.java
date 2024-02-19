
package com.idealighter.game.core.service.log.struct.game;

import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.log.core.PlayerLog;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.TableType;

/**
 * 台费日志 .
 */
@LogTable(type = TableType.DAY)
public class GameAfeeLog extends PlayerLog {

  @Column(type = "int", size = 11, remark = "游戏")
  public final int game;
  @Column(type = "varchar", size = 24, remark = "游戏名称")
  public final String gameName;
  @Column(type = "int", size = 11, remark = "房间")
  public final int room;
  @Column(type = "int", size = 11, remark = "桌子")
  public final int table;
  @Column(type = "int", size = 11, remark = "类型(0:扣除台费,1:退还台费)")
  public final int type;
  @Column(type = "int", size = 11, remark = "台费")
  public final int afee;

  /**
   * 构造函数.
   * 
   * @param player 玩家.
   * @param table .
   */
  public GameAfeeLog(Player player, AbstractTable table, int afee) {
    super(player);
    this.game = table.room().game().getType();
    this.gameName = table.room().game().getDesc();
    this.room = table.room().getId();
    this.table = table.getId();
    this.type = 0;
    this.afee = afee;
  }

  public GameAfeeLog(Player player, AbstractTable table, boolean returnBack, int afee) {
    super(player);
    this.game = table.room().game().getType();
    this.gameName = table.room().game().getDesc();
    this.room = table.room().getId();
    this.table = table.getId();
    this.type = returnBack ? 1 : 0;
    this.afee = afee;
  }
}
