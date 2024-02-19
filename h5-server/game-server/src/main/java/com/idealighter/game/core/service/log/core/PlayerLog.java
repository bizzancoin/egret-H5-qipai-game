
package com.idealighter.game.core.service.log.core;

import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogIndexKey;
import com.idealighter.game.dblog.annotation.LogIndexKeyField;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.IndexType;

/**
 * 玩家日志基类 .
 * 
 * @date 2015年8月28日 下午6:13:12
 *
 */
@LogIndexKey(type = IndexType.NORMAY, keyName = "idx_isPlayer_playerId_time",
    fields = { @LogIndexKeyField(field = "isPlayer", order = 1),
        @LogIndexKeyField(field = "playerId", order = 2),
        @LogIndexKeyField(field = "time", order = 3) })
@LogIndexKey(type = IndexType.NORMAY, keyName = "idx_isPlayer_superId_time",
    fields = { @LogIndexKeyField(field = "isPlayer", order = 1),
        @LogIndexKeyField(field = "superId", order = 2),
        @LogIndexKeyField(field = "time", order = 3) })
@LogIndexKey(type = IndexType.NORMAY, keyName = "time",
    fields = { @LogIndexKeyField(field = "time") })
public abstract class PlayerLog extends DbLog {

  @Column(type = "bigint", size = 20, remark = "玩家id")
  public final long playerId;

  @Column(type = "bigint", size = 20, remark = "玩家id")
  public final long superId;

  @Column(type = "varchar", size = 45, remark = "角色名(昵称)")
  public final String playerName;

  @Column(type = "bit", size = 1, remark = "是否是玩家")
  public final boolean isPlayer;

  @Column(type = "varchar", size = 64, remark = "用户名")
  public final String userName;

  @Column(type = "int", size = 11, remark = "玩家等级")
  public final int level;

  @Column(type = "bigint", size = 20, remark = "标识玩家每次请求msg的动作id")
  public long actionId;

  @Column(type = "varchar", size = 10, remark = "渠道id")
  public final String channelId;



  public PlayerLog(Player player) {
    this(player.playerBo());
    this.actionId = player.actionId;
  }

  /**
   * 构造函数 .
   * 
   * @param domain .
   */
  public PlayerLog(PlayerBo domain) {
    this(domain.getId(), domain.getSuperId(), domain.getPlayerName(), domain.getUserName(),
        domain.getLevel(), 0, !domain.isRobot(), domain.getChannelId());
  }

  /**
   * 构造函数 .
   * 
   * @param playerId 玩家id.
   * @param playerName .
   * @param userName .
   * @param level .
   * @param actionId .
   * @param isPlayer .
   */
  private PlayerLog(long playerId, long superId, String playerName, String userName, int level,
      long actionId, boolean isPlayer, String channelId) {
    this.playerId = playerId;
    this.superId = superId;
    this.playerName = playerName;
    this.userName = userName;
    this.level = level;
    this.isPlayer = isPlayer;
    this.channelId = channelId;
  }
}
