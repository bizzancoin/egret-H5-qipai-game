
package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

/**
 * 座位信息 .
 *
 */
@Data
public class SeatInfo {
  // 座位顺序(0-2)
  @Protobuf(order = 1)
  private int order;
  // 桌子id
  @Protobuf(order = 2)
  private int tableId;
  // 玩家id(负数:机器人,0:代表座位上没有人)
  @Protobuf(order = 3)
  private long playerId;
  // 玩家name
  @Protobuf(order = 4)
  private String playerName;
  // 剩余筹码
  @Protobuf(order = 5)
  private long chips;
  // 0:男,非0:女
  @Protobuf(order = 6)
  private int sex;
  // 玩家头像
  @Protobuf(order = 7)
  private int icon;
  // 离线(即被系统托管),0:没有离线,非0:离线
  @Protobuf(order = 8)
  private int offline;
}
