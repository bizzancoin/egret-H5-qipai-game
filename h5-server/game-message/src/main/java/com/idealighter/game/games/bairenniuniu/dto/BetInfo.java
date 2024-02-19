
package com.idealighter.game.games.bairenniuniu.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 下注信息 .
 *
 */
@Data
public class BetInfo {
  // 下注区域
  @Protobuf(order = 1)
  private int area;
  // 下注筹码
  @Protobuf(order = 2)
  private long chips;
}
