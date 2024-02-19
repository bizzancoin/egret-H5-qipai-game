
package com.idealighter.game.core.tuple;

/**
 * 3元祖封装.
 *
 */
public class Triplet<A, B, C> extends Pair<A, B> {

  public final C v3;

  /**
   * 构造函数.
   * 
   * @param v1 .
   * @param v2 .
   * @param v3 .
   */
  public Triplet(A v1, B v2, C v3) {
    super(v1, v2);
    this.v3 = v3;
  }
}
