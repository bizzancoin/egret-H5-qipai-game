package com.idealighter.game.login.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

@Data
public class GameInfoDto {
  @Protobuf(order = 1)
  private int gameType; // 3水浒传 23 水果拉霸
}
