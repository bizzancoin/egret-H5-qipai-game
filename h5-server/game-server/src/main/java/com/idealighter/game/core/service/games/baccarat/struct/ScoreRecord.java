package com.idealighter.game.core.service.games.baccarat.struct;

/**
 * 百家乐成绩记录枚举 .
 */
public enum ScoreRecord {
  /**
   * 庄普通牌获胜 .
   */
  BANK_NORMAL(0, "庄普通牌获胜"),
  /**
   * 庄赢闲为对 .
   */
  BANKER_PLAYER_PAIRE(1, "庄赢闲为对"),
  /**
   * 庄赢闲为对 .
   */
  BANKER_PAIRE(2, "庄赢庄对"),

  /**
   * 庄赢庄对闲对 .
   */
  BANKER_PAIRE_PLAYER_PAIRE(3, "庄赢庄对闲对"),

  /**
   * 和 .
   */
  TIE(4, "和"),

  /**
   * 和闲对 .
   */
  TIE_PLAYER_PAIRE(5, "和闲对"),

  /**
   * 和庄对 .
   */
  TIE_BANKER_PAIRE(6, "和庄对"),

  /**
   * 和庄对闲对 .
   */
  TIE_BANKER_PAIRE_PLAYER_PAIRE(7, "和庄对闲对"),

  /**
   * 闲赢 .
   */
  PLAYER_NORMAL(8, "闲赢"),

  /**
   * 闲赢闲对 .
   */
  PALYER_PAIRE(9, "闲赢闲对"),

  /**
   * 闲赢庄对 .
   */
  PLAYER_BANKER_PAIRE(10, "闲赢庄对"),

  /**
   * 闲赢闲对庄对 .
   */
  PALYER_PAIRE_BANKER_PAIRE(11, "闲赢闲对庄对");

  public final int type;
  public final String desc;

  ScoreRecord(int type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  /**
   * .
   * 
   * @param type .
   * @return
   */
  public static ScoreRecord getBettingType(int type) {
    for (ScoreRecord betType : ScoreRecord.values()) {
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
