
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 玩家出牌信息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_PLAY_CARD)
@Data
public class ResPlayCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_PLAY_CARD;
  // 出牌的玩家
  @Protobuf(order = 2)
  private long playerId;
  // 出牌的玩家order
  @Protobuf(order = 3)
  private int order;
  // 玩家出的牌
  @Protobuf(fieldType = FieldType.INT32, order = 4)
  private List<Integer> cards = new ArrayList<>();
  // 玩家出的牌的类型(1:单牌，2:对子,3:三不带,4:三代单，5:三带对,6:单顺,7:双顺,8:三顺,9:飞机带单,10:飞机带队,11:炸弹,12:王炸,13:四带单,14:四带队)
  @Protobuf(order = 5)
  private int cardsType;
  // 下一个出牌玩家的顺序
  @Protobuf(order = 6)
  private int nextPlayOrder;
  // 下一个出牌玩家编号
  @Protobuf(order = 7)
  private long nextPlayPlayerId;
  // 下一个出牌玩家出牌时间
  @Protobuf(order = 8)
  private int nextPlayTimeLimit;
}
