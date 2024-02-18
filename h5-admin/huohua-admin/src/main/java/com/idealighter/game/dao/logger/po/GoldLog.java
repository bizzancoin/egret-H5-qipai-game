package com.idealighter.game.dao.logger.po;

import java.util.Date;

import lombok.Data;

@Data
public class GoldLog {
  private Integer id;

  /**
   * 改变前
   */
  private Long before;

  /**
   * 改变后
   */
  private Long after;

  /**
   * 改变的值(正：增加，负：减少)
   */
  private Long change;

  /**
   * 改变的原因
   */
  private String reason;

  /**
   * 玩家id
   */
  private Long playerId;

  private Long superId;

  /**
   * 角色名(昵称)
   */
  private String playerName;

  /**
   * 是否是玩家
   */
  private Boolean isPlayer;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 玩家等级
   */
  private Integer level;

  /**
   * 标识玩家每次请求msg的动作id
   */
  private Long actionId;

  /**
   * 时间
   */
  private Date time;

  private String channelId;
}
