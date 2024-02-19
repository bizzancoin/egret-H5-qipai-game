
package com.idealighter.game.plaza.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 游戏类型信息 .
 *
 */
@Data
public class GameType {
  // 游戏类型(0:我的游戏;1:扑克厅;2:麻将厅;3:街机厅;4:比赛厅;5:休闲厅;6:所有游戏;7:推荐游戏)
  @Protobuf(order = 1)
  private int type;
  // 位置索引
  @Protobuf(order = 2)
  private int index;
  // 游戏列表
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  private List<Integer> games = new ArrayList<>();
}
