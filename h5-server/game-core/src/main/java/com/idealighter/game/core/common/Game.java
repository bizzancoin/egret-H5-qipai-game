
package com.idealighter.game.core.common;

/**
 * 游戏枚举.
 *
 */
public enum Game {



  /**
   * BAIREN_NIUNIU 百人牛牛.
   */
  BAIREN_NIUNIU(GameIdConstant.BAIREN_NIUNIU, "百人牛牛", 60, "bairen_niuniu"),

  /**
   * JCBY 金蟾捕鱼.
   */
  JCBY(GameIdConstant.JCBY, "金蟾捕鱼", 54, "jcby"),


  /**
   * BACCARAT 百家乐.
   */
  BACCARAT(GameIdConstant.BACCARAT, "百家乐", 59, "baccarat"),

  DDZ(GameIdConstant.DDZ, "斗地主(金币场)", 62, "ddz"),

  ;
  // 游戏类型id
  private final int type;
  // 游戏描述
  private final String desc;
  // 游戏模块id，通过它可以获取游戏线程执行器
  private final int moduleId;
  // tag
  private final String tag;

  /**
   * 构造函数.
   * 
   * @param type .
   * @param desc .
   * @param moduleId .
   */
  private Game(int type, String desc, int moduleId, String tag) {
    this.type = type;
    this.desc = desc;
    this.moduleId = moduleId;
    this.tag = tag;
  }


  /**
   * 获取游戏类型.
   * 
   * @param type .
   * @return .
   */
  public static Game getGame(int type) {
    for (Game game : Game.values()) {
      if (game.type == type) {
        return game;
      }
    }

    return null;
  }

  @Override
  public String toString() {
    return type + "_" + desc;
  }


  public int getType() {
    return type;
  }


  public String getDesc() {
    return desc;
  }


  public int getModuleId() {
    return moduleId;
  }


  public String getTag() {
    return tag;
  }
}
