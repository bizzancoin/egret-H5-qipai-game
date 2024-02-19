
package com.idealighter.game.games.baccarat.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 牌信息 .
 *
 */
@Data
public class CardsInfo {
  // 庄家闲家牌
  @Protobuf(fieldType = FieldType.INT32, order = 1)
  private List<Integer> cards = new ArrayList<>();
  // 0庄家，1闲家
  @Protobuf(order = 2)
  private int id;
  // 牌型
  @Protobuf(order = 3)
  private int cardsType;
  // 点数
  @Protobuf(order = 4)
  private int point;
}
