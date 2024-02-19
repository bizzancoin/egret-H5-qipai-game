
package com.idealighter.game.games.bairenniuniu.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 百人牛牛房间类型明细信息 .
 *
 */
@Data
public class RoomTypeDetailInfo {
  // 房间类型id
  @Protobuf(order = 1)
  private int type;
  // 房间类型名称
  @Protobuf(order = 2)
  private String typeName;
  // 房间
  @Protobuf(order = 3)
  private List<RoomInfo> rooms = new ArrayList<>();
}
