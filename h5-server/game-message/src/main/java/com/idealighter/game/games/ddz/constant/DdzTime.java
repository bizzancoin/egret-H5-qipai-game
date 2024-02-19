package com.idealighter.game.games.ddz.constant;

public enum DdzTime {
  NO_START(0, "游戏未开始"),

  READY(1, "准备阶段"),

  DEAL_CARD(2, "发牌阶段"),

  CALL_DZ(3, "叫地主抢地主阶段"),

  CALL_MULTIPLE(4, "加倍阶段"),

  PLAY_CARD(5, "玩牌阶段"),

  GAME_OVER(6, "结束阶段");

  public final int val;
  public final String desc;

  private DdzTime(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }
}
