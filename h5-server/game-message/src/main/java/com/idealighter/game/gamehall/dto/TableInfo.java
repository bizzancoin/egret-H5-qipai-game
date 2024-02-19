
package com.idealighter.game.gamehall.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 牌桌信息.
 */
@Data
public class TableInfo {
  // 桌子id
  @Protobuf(order = 1)
  private int id;
  // 所属房间id
  @Protobuf(order = 2)
  private int roomId;
  // 0:没有密码,非0:有密码
  @Protobuf(order = 3)
  private int hasPwd;
  // 牌桌的座位信息
  @Protobuf(order = 4)
  private List<SeatInfo> seats = new ArrayList<>();
}
