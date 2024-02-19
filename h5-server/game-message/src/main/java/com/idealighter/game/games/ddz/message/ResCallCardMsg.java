
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家叫牌 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_CALL_CARD)
@Data
public class ResCallCardMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_CALL_CARD;
  // 叫牌的玩家
  @Protobuf(order = 2)
  private long playerId;
  // 叫牌的玩家order
  @Protobuf(order = 3)
  private int order;
  // 叫牌类型(0:不叫地主,1:叫地主,2:不抢地主,3:抢地主)
  @Protobuf(order = 4)
  private int type;
  // 地主玩家id(0代表还没有确定地主)
  @Protobuf(order = 5)
  private long landlord;
  // 地主order
  @Protobuf(order = 6)
  private int landlordOrder;
  // 下一个叫牌的座位顺序号(0-2)
  @Protobuf(order = 7)
  private int nextCallOrder;
  @Protobuf(order = 8)
  private long nextCallPlayer;
  @Protobuf(order = 9)
  private int nextCallTimeLimit;
}
