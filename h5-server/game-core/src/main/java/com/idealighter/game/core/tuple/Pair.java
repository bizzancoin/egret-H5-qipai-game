
package com.idealighter.game.core.tuple;

/**
 * 二元祖封装.
 *
 */
public class Pair<A, B> {

  public final A v1;
  public final B v2;

  /**
   * 构造函数.
   * 
   * @param v1 .
   * @param v2 .
   */
  public Pair(A v1, B v2) {
    this.v1 = v1;
    this.v2 = v2;
  }
}
