
package com.idealighter.game.core.service.prizepool.struct;

/**
 * 奖池类型 .
 * 
 * @date 2015年10月16日 下午4:35:48
 *
 */
public enum RoomPrizePoolType {

  DEFAULT(0, "默认奖池"), GOLD_RANGE(1, "金币范围奖池");

  public final int type;
  public final String desc;

  /**
   * 构造函数 .
   * 
   * @param type .
   * @param desc .
   */
  private RoomPrizePoolType(int type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  /**
   * 获取类型 .
   * 
   * @return
   */
  public static RoomPrizePoolType getPoolType(int type) {
    for (RoomPrizePoolType poolType : values()) {
      if (poolType.type == type) {
        return poolType;
      }
    }

    return null;
  }

}
