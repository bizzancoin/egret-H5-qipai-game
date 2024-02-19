
package com.idealighter.game.core.service.prizepool.struct;

import com.idealighter.game.core.common.Game;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 奖池 .
 * 
 * @date 2015年10月15日 下午3:20:39
 *
 */
public abstract class PrizePool {
  // 游戏
  protected int game;
  // 奖池金额
  protected AtomicLong prize = new AtomicLong();

  /**
   * 游戏 .
   * 
   * @return
   */
  public Game game() {
    return Game.getGame(game);
  }

  public int getGame() {
    return game;
  }

  public void setGame(int game) {
    this.game = game;
  }

  public AtomicLong getPrize() {
    return prize;
  }

  public void setPrize(AtomicLong prize) {
    this.prize = prize;
  }
}
