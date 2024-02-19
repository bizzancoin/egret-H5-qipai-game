package com.idealighter.game.games.jcby.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 鱼死亡信息 .
 *
 */
@Data
public class FishDieInfo {
  // 鱼id
  @Protobuf(order = 1)
  private int fishId;
  // 分数
  @Protobuf(order = 2)
  private int score;
}
