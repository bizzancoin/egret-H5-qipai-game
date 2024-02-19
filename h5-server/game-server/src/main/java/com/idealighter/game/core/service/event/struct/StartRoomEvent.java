
package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.common.Game;

import lombok.Data;

@Data
public class StartRoomEvent {
  // 游戏
  private final Game game;
  // 房间id
  private final int roomId;

  /**
   * 构造函数 .
   * 
   * @param game .
   * @param roomId .
   */
  public StartRoomEvent(Game game, int roomId) {
    this.game = game;
    this.roomId = roomId;
  }

}
