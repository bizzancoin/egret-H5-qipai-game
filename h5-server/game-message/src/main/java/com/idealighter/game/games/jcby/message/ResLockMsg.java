
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家请求锁定结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_LOCK)
@Data
public class ResLockMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_LOCK;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
  // 位置
  @Protobuf(order = 3)
  private int order;
  // 锁定鱼
  @Protobuf(order = 4)
  private int fishId;
  @Protobuf(order = 5)
  private int angle;
}
