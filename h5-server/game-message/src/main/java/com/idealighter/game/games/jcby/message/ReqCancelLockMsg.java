
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家取消锁定 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.CANCEL_LOCK)
@Data
public class ReqCancelLockMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.CANCEL_LOCK;
}
