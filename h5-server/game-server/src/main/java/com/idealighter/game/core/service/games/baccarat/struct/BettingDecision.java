package com.idealighter.game.core.service.games.baccarat.struct;

/**
 * 百家乐下注类型.
 *
 */
public enum BettingDecision {
  /**
   * 庄家赢.
   */
  BANK(0, "庄"),
  /**
   * 闲家赢.
   */
  PLAYER(1, "闲"),
  /**
   * 平局.
   */
  TIE(2, "平局"),

  /**
   * 大big.
   */
  BIG(3, "大BIG"),

  /**
   * 闲对.
   */
  PLAYER_PAIRE(4, "闲对"),

  /**
   * 龙.
   */
  DRAGON(5, "龙"),

  /**
   * 闲宝龙.
   */
  PLAYER_DRAGON(6, "闲宝龙"),

  /*
   * 小SMALL .
   */
  SMALL(7, "小SMALL"),

  /*
   * 虎 .
   */
  TIGER(8, "虎"),

  /*
   * 庄对 .
   */
  BANKER_PAIRE(9, "庄对"),

  /**
   * 庄宝龙.
   */
  BANKER_DRAGON(10, "庄宝龙"),

  /**
   * 龙虎和.
   */
  DRAGON_TIGER_TIE(11, "龙虎和");

  public final int type;
  public final String desc;

  BettingDecision(int type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  /**
   * 下注类型.
   * 
   * @param type . type.
   * @return
   */
  public static BettingDecision getBettingType(int type) {
    for (BettingDecision betType : BettingDecision.values()) {
      if (betType.type == type) {
        return betType;
      }
    }
    return null;
  }

  @Override
  public String toString() {

    return name() + "(" + desc + ")";
  }
}
