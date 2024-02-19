package com.idealighter.game.admin.common.controller.dto;

import lombok.Data;

@Data
public class GameListDto {
  private int type;
  // 游戏描述
  private String desc;
  // 游戏模块id，通过它可以获取游戏线程执行器
  private int moduleId;
  // tag
  private String tag;
}
