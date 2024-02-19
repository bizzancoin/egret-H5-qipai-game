
package com.idealighter.game.core.service.prizepool.struct.room;

/**
 * 金币范围奖池 .
 * 
 * @date 2015年10月15日 下午10:02:57
 *
 */
public class GoldRangeRoomPrizePool extends RoomPrizePool {
  // 奖池编号
  private long id;
  // 范围下限
  private long lower;
  // 范围上限
  private long upper;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLower() {
    return lower;
  }

  public void setLower(long lower) {
    this.lower = lower;
  }

  public long getUpper() {
    return upper;
  }

  public void setUpper(long upper) {
    this.upper = upper;
  }

}
