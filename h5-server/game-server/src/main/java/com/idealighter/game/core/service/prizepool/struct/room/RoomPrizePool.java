
package com.idealighter.game.core.service.prizepool.struct.room;

import com.idealighter.game.core.service.prizepool.struct.PrizePool;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间奖池 .
 * 
 * @date 2015年10月15日 下午3:48:23
 *
 */
public class RoomPrizePool extends PrizePool {
  // 房间编号
  private int room;
  // 奖池提取策略
  private List<ControlStrategy> strategys = new ArrayList<>();

  public RoomPrizePool() {}

  /**
   * 当前有效的提取策略 .
   * 
   * @return
   */
  public ControlStrategy abledControlStrategy() {
    if (strategys != null) {
      for (ControlStrategy strategy : strategys) {
        if (prize.get() >= strategy.getLower() && prize.get() <= strategy.getUpper()) {
          return strategy;
        }
      }
    }

    return null;
  }

  /**
   * 当前有效的提取策略 .
   * 
   * @return
   */
  public ControlStrategy abledControlStrategy(long gold) {
    if (strategys != null) {
      for (ControlStrategy strategy : strategys) {
        if (gold >= strategy.getLower() && gold <= strategy.getUpper()) {
          return strategy;
        }
      }
    }

    return null;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public List<ControlStrategy> getStrategys() {
    return strategys;
  }

  public void setStrategys(List<ControlStrategy> strategys) {
    this.strategys = strategys;
  }
}
