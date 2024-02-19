package com.idealighter.game.core.service.prizepool.struct.control;

import com.idealighter.game.core.annotation.GameCtl;
import com.idealighter.game.core.common.GameIdConstant;

/**
 * 百家乐的控制 .
 */
@GameCtl(GameIdConstant.BACCARAT)
public class BaccaratControl implements GameControl {
  // 输比例
  private int loseRatio;
  // 赢比例
  private int winRatio;

  // 开闲比例
  private int tieRito;

  /**
   * 构造函数.
   * 
   * @param loseRatio .
   * @param winRatio .
   */
  public BaccaratControl(int loseRatio, int winRatio, int tieRito) {
    super();
    this.loseRatio = loseRatio;
    this.winRatio = winRatio;
    this.tieRito = tieRito;
  }

  public BaccaratControl() {}

  public int getLoseRatio() {
    return loseRatio;
  }

  public void setLoseRatio(int loseRatio) {
    this.loseRatio = loseRatio;
  }

  public int getWinRatio() {
    return winRatio;
  }

  public void setWinRatio(int winRatio) {
    this.winRatio = winRatio;
  }

  public int getTieRito() {
    return tieRito;
  }

  public void setTieRito(int tieRito) {
    this.tieRito = tieRito;
  }
}
