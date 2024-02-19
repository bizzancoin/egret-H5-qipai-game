package com.idealighter.game.games.bairenniuniu.dto;

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
  // 0-4 :0庄家的牌，1:闲家1，2:闲家2，3:闲家3，4:闲家4
  @Protobuf(order = 1)
  private int id;
  // 牌型(0:没牛，1-10:牛1到牛牛,四花:11,四炸:12,五花:13,五小:14)
  @Protobuf(order = 2)
  private int cardsType;
  // 玩家的牌，已经是最优牌型(3+2)
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  private List<Integer> cards = new ArrayList<>();


}
