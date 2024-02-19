package com.idealighter.game.core.service.games.bairenniuniu.struct;


public enum GameStatus {
  REST(1, "休息"), BET(2, "下注"), REWARD(3, "发牌");
  private final int val;
  private final String desc;

  public int getVal() {
    return val;
  }

  public String getDesc() {
    return desc;
  }

  /**
   * 构造函数.
   * 
   * @param val .
   * @param desc .
   */
  private GameStatus(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }

}
