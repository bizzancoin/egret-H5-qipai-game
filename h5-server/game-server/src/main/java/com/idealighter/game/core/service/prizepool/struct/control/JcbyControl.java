
package com.idealighter.game.core.service.prizepool.struct.control;

import com.idealighter.game.core.annotation.GameCtl;
import com.idealighter.game.core.common.GameIdConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 金蝉捕鱼控制 .
 * 
 * @date 2016年4月5日 下午1:41:33
 *
 */
@GameCtl(GameIdConstant.JCBY)
public class JcbyControl implements GameControl {

  // 默认难度系数
  public static final int DEFAULT_DIFFICULTY = 10000;

  // 鱼的难度
  private List<FishDifficulty> difficultys = new ArrayList<>();

  public JcbyControl() {}

  public List<FishDifficulty> getDifficultys() {
    return difficultys;
  }

  public void setDifficultys(List<FishDifficulty> difficultys) {
    this.difficultys = difficultys;
  }
}
