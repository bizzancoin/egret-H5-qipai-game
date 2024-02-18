package com.idealighter.game.service.common.sto;

import lombok.Data;

@Data
public class GameListItemSto {
  // 游戏类型id
  private int type;
  // 游戏描述
  private String desc;
  // 游戏模块id，通过它可以获取游戏线程执行器
  private int moduleId;
  // tag
  private String tag;
}
