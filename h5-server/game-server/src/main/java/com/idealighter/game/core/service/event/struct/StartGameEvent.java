
package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.common.Game;

import lombok.Data;

@Data
public class StartGameEvent {
  // 游戏
  private final Game game;

  /**
   * 构造函数 .
   * 
   * @param game .
   */
  public StartGameEvent(Game game) {
    this.game = game;
  }

}
