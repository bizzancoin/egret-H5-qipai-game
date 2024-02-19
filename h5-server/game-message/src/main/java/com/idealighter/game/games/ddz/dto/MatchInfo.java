
package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 比赛信息 .
 *
 */
@Data
public class MatchInfo {
  // 1:快速赛,2:定时赛
  @Protobuf(order = 1)
  private int type;
  // 比赛人数
  @Protobuf(order = 2)
  private int matchNum;
  // 已报名玩家人数
  @Protobuf(order = 3)
  private int appliedNum;
  // 剩余开赛时间(秒,定时赛才有效)
  @Protobuf(order = 4)
  private int leftTime;
  // 开赛时间(时间戳,只能表示2036年)
  @Protobuf(order = 5)
  private int matchTime;
  // 奖励字符串
  @Protobuf(order = 6)
  private String reward;
}
