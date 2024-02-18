package com.idealighter.game.service.game.bo;

import lombok.Data;

@Data
public class GameBo {
  /**
   * id.
   */
  private Integer id;

  /**
   * 游戏名称.
   */
  private String name;

  /**
   * 房间表.
   */
  private String roomTable;

  /**
   * 描述.
   */
  private String remark;

  /**
   * 0不启动 1启动.
   */
  private Boolean active;

  /**
   * 是否需要机器人.
   */
  private Boolean needRobot;

  /**
   * 是否为局数游戏.
   */
  private Boolean roundGame;

  /**
   * 是否含有个人控制.
   */
  private Boolean personControl;

  /**
   * 是否含有房间控制.
   */
  private Boolean roomControl;
}
