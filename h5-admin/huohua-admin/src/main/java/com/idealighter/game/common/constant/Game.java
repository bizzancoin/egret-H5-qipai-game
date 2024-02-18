
package com.idealighter.game.common.constant;

/**
 * 游戏枚举 .
 * 
 * 
 * @date 2015年9月14日 下午4:14:36
 *
 */
public enum Game {

  /**
   * DOU_DI_ZHU 斗地主.
   */
  DOU_DI_ZHU(1, "斗地主", 501),

  /**
   * ERREN_NIUNIU 二人牛牛.
   */
  ERREN_NIUNIU(2, "二人牛牛", 502),

  /**
   * SHUI_HU_ZHUAN 水浒传.
   */
  SHUI_HU_ZHUAN(3, "水浒传", 503),

  /**
   * FOURS_SHOP 4s店.
   */
  FOURS_SHOP(4, "4s店", 504),

  /**
   * TEXAPOKER 德州扑克.
   */
  TEXAPOKER(5, "德州扑克", 505),

  /**
   * HAPPY_FIVE 欢乐五张.
   */
  HAPPY_FIVE(6, "欢乐五张", 507),

  /**
   * BAIREN_NIUNIU 百人牛牛.
   */
  BAIREN_NIUNIU(7, "百人牛牛", 508),

  /**
   * JCBY 金蟾捕鱼.
   */
  JCBY(9, "金蟾捕鱼", 520),

  /**
   * FQZS 飞禽走兽.
   */
  FQZS(10, "飞禽走兽", 509),

  /**
   * LKBY 李逵捕鱼.
   */
  LKBY(12, "李逵捕鱼", 521),

  /**
   * SIREN_NIUNIU 四人牛牛.
   */
  SIREN_NIUNIU(13, "四人牛牛", 506),

  /**
   * TONGBI_NIUNIU 通比牛牛.
   */
  TONGBI_NIUNIU(14, "通比牛牛", 514),

  /**
   * ERREN_TEXAPOKER 二人德州扑克.
   */
  ERREN_TEXAPOKER(15, "二人德州扑克", 515),

  /**
   * HORCERACE 赛马.
   */
  HORCERACE(16, "赛马", 516),

  /**
   * ZJH 扎金花.
   */
  ZJH(17, "炸金花", 510),

  /**
   * BACCARAT 百家乐.
   */
  BACCARAT(18, "百家乐", 518),

  /**
   * XZDD 血战到底.
   */
  XZDD(19, "血战到底", 519),

  /**
   * WEISHUWU 魏蜀吴.
   */
  WEISHUWU(20, "魏蜀吴", 522),

  /**
   * WKNH 悟空闹海.
   */
  WKNH(21, "悟空闹海", 523),

  /**
   * ODERMA 定制麻将.
   */
  ODERMA(22, "定制麻将", 524),

  /**
   * FRUIT_SLOT 水果拉霸.
   */
  FRUIT_SLOT(23, "水果拉霸", 525),

  /**
   * BLACK_JACK 21点.
   */
  BLACK_JACK(24, "21点", 528),

  /**
   * BLACK_JACK 21点.
   */
  DDZ(25, "斗地主", 528);

  // 游戏类型id
  public final int type;
  // 游戏描述
  public final String desc;
  // 游戏模块id，通过它可以获取游戏线程执行器
  public final int moduleId;

  /**
   * 游戏枚举.
   * 
   * @param type .
   * @param desc .
   * @param moduleId .
   */
  private Game(int type, String desc, int moduleId) {
    this.type = type;
    this.desc = desc;
    this.moduleId = moduleId;
  }


  /**
   * 获取游戏类型 .
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
}
