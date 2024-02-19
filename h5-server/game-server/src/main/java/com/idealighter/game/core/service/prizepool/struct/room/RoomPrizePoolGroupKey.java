package com.idealighter.game.core.service.prizepool.struct.room;

import lombok.Data;

@Data
public class RoomPrizePoolGroupKey {
  // 游戏
  protected Integer game;
  // 房间
  protected Integer roomId;

  public RoomPrizePoolGroupKey() {}

  /**
   * 构造函数 .
   * 
   * @param game . 游戏.
   * @param roomId . 房间Id.
   */
  public RoomPrizePoolGroupKey(Integer game, Integer roomId) {
    this.game = game;
    this.roomId = roomId;
  }

  @Override
  public int hashCode() {
    int res = 17;
    res = 37 * res + game;
    res = 37 * res + roomId;

    return res;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof RoomPrizePoolGroupKey)) {
      return false;
    }

    RoomPrizePoolGroupKey key = (RoomPrizePoolGroupKey) obj;
    return key.game == game && key.roomId == roomId;
  }
}
