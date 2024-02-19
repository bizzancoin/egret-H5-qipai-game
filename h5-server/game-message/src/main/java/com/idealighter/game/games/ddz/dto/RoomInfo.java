
package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;

/**
 * 房间信息 .
 *
 */
@Data
public class RoomInfo {
  // 房间id
  @Protobuf(order = 1)
  private int roomId;
  // 房间名称
  @Protobuf(order = 2)
  private String name;
  // 房间类型
  @Protobuf(order = 3)
  private int type;
  @Protobuf(order = 4)
  private int maxNum;
  // 进入下限
  @Protobuf(order = 5)
  private long lower;
  // 进入上限
  @Protobuf(order = 6)
  private long upper;
  // 金币比例
  @Protobuf(order = 7)
  private int proportionGold;
  // 筹码比例
  @Protobuf(order = 8)
  private int proportionChips;
  // 桌子数
  @Protobuf(order = 9)
  private int table;
  // 底注
  @Protobuf(order = 10)
  private long baseBet;
  // 单局台费
  @Protobuf(order = 11)
  private int afee;
  // 进入类型（0点击入座，1自动分配）
  @Protobuf(order = 12)
  private int inType;
  // 玩家人数
  @Protobuf(order = 13)
  private int playerNum;
}
