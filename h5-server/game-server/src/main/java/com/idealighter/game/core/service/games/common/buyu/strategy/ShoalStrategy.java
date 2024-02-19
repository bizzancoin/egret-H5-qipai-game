
package com.idealighter.game.core.service.games.common.buyu.strategy;

import java.util.List;

/**
 * 鱼阵刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class ShoalStrategy implements Strategy {
  // 策略
  private List<Integer> strategys;

  public List<Integer> getStrategys() {
    return strategys;
  }

  public void setStrategys(List<Integer> strategys) {
    this.strategys = strategys;
  }

  @Override
  public StrategyType type() {
    return StrategyType.SHOAL;
  }

}
