
package com.idealighter.game.plaza.dto;

import lombok.Data;

/**
 * 游戏信息 .
 *
 */
@Data
public class GameInfo {

  // 游戏id
  private int id;

  // 是否火热(0:不火热,非0:火热)
  private int hot;

}
