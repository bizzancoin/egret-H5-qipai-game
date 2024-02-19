package com.idealighter.game.core.service.prizepool.struct.control;

import com.idealighter.game.core.annotation.GameCtl;
import com.idealighter.game.core.common.GameIdConstant;

/**
 * 百人牛牛的控制.
 *
 */
@GameCtl(GameIdConstant.BAIREN_NIUNIU)
public class BaiRenNiuNiuControl implements GameControl {
  // 输比例
  private int loseRatio;
  // 赢比例
  private int winRatio;

  /**
   * 构造函数.
   * 
   * @param loseRatio . 输了比例.
   * @param winRatio . 赢了比例.
   */
  public BaiRenNiuNiuControl(int loseRatio, int winRatio) {
    super();
    this.loseRatio = loseRatio;
    this.winRatio = winRatio;
  }

  public BaiRenNiuNiuControl() {}

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
}
