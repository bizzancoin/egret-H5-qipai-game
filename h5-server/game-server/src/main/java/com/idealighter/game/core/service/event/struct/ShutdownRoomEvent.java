
package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.common.Game;

import lombok.Data;

/**
 * 关闭房间事件 .
 * 
 * @date 2016年3月21日 下午1:49:49
 *
 */
@Data
public class ShutdownRoomEvent {
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
  public ShutdownRoomEvent(Game game, int roomId) {
    this.game = game;
    this.roomId = roomId;
  }

}
