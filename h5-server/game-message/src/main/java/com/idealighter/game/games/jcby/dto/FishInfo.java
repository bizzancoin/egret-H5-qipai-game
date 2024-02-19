
package com.idealighter.game.games.jcby.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 鱼信息 .
 *
 */
@Data
public class FishInfo {
  // 鱼id
  @Protobuf(order = 1)
  private int id;
  // 鱼的类型
  @Protobuf(order = 2)
  private int type;
  // 起点x
  @Protobuf(order = 3)
  private int xcoord;
  // 起点y
  @Protobuf(order = 4)
  private int ycoord;
  // 路径id
  @Protobuf(order = 5)
  private int road;
  // 时间
  @Protobuf(order = 6)
  private long time;
  // 夹角(负数不处理)
  @Protobuf(order = 7)
  private int angle;
}
