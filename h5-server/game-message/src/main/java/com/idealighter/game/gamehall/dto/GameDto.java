package com.idealighter.game.gamehall.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

@Data
public class GameDto {
  @Protobuf(order = 1)
  private Integer gameId;
  @Protobuf(order = 2)
  private String name;
}
