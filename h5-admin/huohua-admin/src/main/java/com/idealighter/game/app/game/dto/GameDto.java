package com.idealighter.game.app.game.dto;

import lombok.Data;

@Data
public class GameDto {


  /**
   * id.
   */
  private Integer id;

  /**
   * 游戏名称.
   */
  private String name;

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
