
package com.idealighter.game.gamehall.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 房间成员信息 .
 *
 */
@Data
public class MemInfo {
  // 玩家id
  @Protobuf(order = 1)
  private long playerId;
  // 玩家昵称
  @Protobuf(order = 2)
  private String playerName;
  // 玩家签名
  @Protobuf(order = 3)
  private String signature;
  // 玩家等级
  @Protobuf(order = 4)
  private int level;
  // 0:男,非0:女
  @Protobuf(order = 5)
  private int sex;
  // 玩家头像
  @Protobuf(order = 6)
  private int icon;
  // 金币
  @Protobuf(order = 7)
  private long gold;
  // 积分
  @Protobuf(order = 8)
  private long cedit;
  // 筹码
  @Protobuf(order = 9)
  private long chips;
  // 状态(0:站立,1:入座,2:准备,3:游戏中)
  @Protobuf(order = 10)
  private int state;
  // 0:不是vip,非0:vip
  @Protobuf(order = 11)
  private int vip;
  // 桌号
  @Protobuf(order = 12)
  private int tableId;
  // 座位号
  @Protobuf(order = 13)
  private int order;
  // 0:不是托管,非0:托管
  @Protobuf(order = 14)
  private int sysHost;
}
