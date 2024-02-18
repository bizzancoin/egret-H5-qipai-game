package com.idealighter.game.service.robotcfg.bo;

import lombok.Data;

@Data
public class RobotCfgListBo {
  /**
   * id.
   */
  private Integer id;
  /**
   * 游戏.
   */
  private Integer game;
  /**
   * 游戏名称.
   */
  private String gameName;
  /**
   * 房间.
   */
  private Integer room;
  /**
   * 房间名称.
   */
  private String roomName;
  /**
   * 是否允许占桌.
   */
  private Byte occupytable;
  /**
   * 是否允许和玩家对局.
   */
  private Byte gamewithplayer;
  /**
   * 初始携带金币下限.
   */
  private Long goldlower;
  /**
   * 初始携带金币上限.
   */
  private Long goldupper;
  /**
   * 最大携带金币数.
   */
  private Long maxgold;
  /**
   * 单桌游戏局数下限.
   */
  private Integer tablegamelower;
  /**
   * 单桌游戏局数上限.
   */
  private Integer tablegameupper;
  /**
   * 游戏总局数下限.
   */
  private Integer totalgamelower;
  /**
   * 游戏总局数上限.
   */
  private Integer totalgameupper;
  /**
   * 0:00~2:00人数.
   */
  private Integer time1players;
  /**
   * 2:00~4:00人数.
   */
  private Integer time2players;
  /**
   * 4:00~6:00人数.
   */
  private Integer time3players;
  /**
   * 6:00~8:00人数.
   */
  private Integer time4players;
  /**
   * 8:00~10:00人数.
   */
  private Integer time5players;
  /**
   * 10:00~12:00人数.
   */
  private Integer time6players;
  /**
   * 12:00~14:00人数.
   */
  private Integer time7players;
  /**
   * 14:00~16:00人数.
   */
  private Integer time8players;
  /**
   * 16:00~18:00人数.
   */
  private Integer time9players;
  /**
   * 18:00~20:00人数.
   */
  private Integer time10players;
  /**
   * 20:00~22:00人数.
   */
  private Integer time11players;
  /**
   * 22:00~24:00人数.
   */
  private Integer time12players;

  /**
   * 配置状态 0未开启 1开启.
   */
  private Byte state;
}
