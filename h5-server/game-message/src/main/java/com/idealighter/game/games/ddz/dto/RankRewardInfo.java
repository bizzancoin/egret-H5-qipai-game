
package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 玩家排名奖励 .
 *
 */
@Data
public class RankRewardInfo {
  // 物品id
  @Protobuf(order = 1)
  private int id;
  // 物品数量
  @Protobuf(order = 2)
  private int num;
}
