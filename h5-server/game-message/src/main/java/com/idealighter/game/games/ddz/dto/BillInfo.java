
package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

/**
 * 玩家结算 .
 *
 */
@Data
public class BillInfo {
  // 玩家id(负数:机器人,0:代表座位上没有人)
  @Protobuf(order = 1)
  private long playerId;
  // 玩家name
  @Protobuf(order = 2)
  private String playerName;
  // 座位顺序(0-2)
  @Protobuf(order = 3)
  private int order;
  // 结算积分
  @Protobuf(order = 4)
  private long chips;
}
