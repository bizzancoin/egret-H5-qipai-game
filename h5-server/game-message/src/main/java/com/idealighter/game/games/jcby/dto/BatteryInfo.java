
package com.idealighter.game.games.jcby.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 炮台信息 .
 *
 */
@Data
public class BatteryInfo {
  // 玩家id
  @Protobuf(order = 1)
  private long playerId;
  // 座位顺序
  @Protobuf(order = 2)
  private int order;
  // 炮管分数
  @Protobuf(order = 3)
  private int score;
  // 炮管数量
  @Protobuf(order = 4)
  private int num;
  // 是否能量炮(0:不是,非0:能量炮)
  @Protobuf(order = 5)
  private int power;
}
