
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 子弹打中鱼 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.HIT)
@Data
public class ReqHitMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.HIT;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
  // 子弹id
  @Protobuf(order = 3)
  private long bulletId;
  // 鱼id
  @Protobuf(order = 4)
  private int fishId;
}
