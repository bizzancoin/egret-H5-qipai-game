package com.idealighter.game.core.service.games.common.buyu;

/**
 * 子弹.
 *
 */
public class Bullet {

  // 子弹id
  public final long id;
  // 分数
  public final int score;
  // 是否能量炮
  public final boolean power;

  /**
   * 子弹构造函数.
   * 
   * @param id .
   * @param score .
   * @param power .
   */
  public Bullet(long id, int score, boolean power) {
    super();
    this.id = id;
    this.score = score;
    this.power = power;
  }

}
