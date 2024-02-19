
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家取消锁定 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_CANCEL_LOCK)
@Data
public class ResCancelLockMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_CANCEL_LOCK;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
  // 位置
  @Protobuf(order = 3)
  private int order;
}
