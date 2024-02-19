
package com.idealighter.game.gamehall.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 座位信息.
 */
@Data
public class SeatInfo {
  // 桌子id
  @Protobuf(order = 1)
  private int tableId;
  // 座位顺序
  @Protobuf(order = 2)
  private int order;
  // 玩家id,0代表座位上没有人
  @Protobuf(order = 3)
  private long playerId;
}
