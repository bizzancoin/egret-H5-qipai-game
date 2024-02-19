package com.idealighter.game.core.service.games.baccarat.struct;

public enum DragonAndTiger {
  DRAGON(1, "龙"), TIE(2, "龙虎和"), TIGER(3, "虎");
  public final int val;
  public final String desc;

  /**
   * 构造函数.
   * 
   * @param val .
   * @param desc .
   */
  private DragonAndTiger(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }

}
