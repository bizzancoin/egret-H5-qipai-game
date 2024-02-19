package com.idealighter.game.core.service.games.baccarat.struct;

public enum GameStatus {
  BET(1, "下注"), REWARD(2, "发牌"), BILL(3, "结算"), WASHCARD(4, "洗牌");
  public final int val;
  public final String desc;

  /**
   * 游戏状态.
   * 
   * @param val .
   * @param desc .
   */
  private GameStatus(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }

}
