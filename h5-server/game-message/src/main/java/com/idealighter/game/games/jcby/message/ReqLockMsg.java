
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家请求锁定 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.LOCK)
@Data
public class ReqLockMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.LOCK;
  // 锁定鱼
  @Protobuf(order = 2)
  private int fishId;
  @Protobuf(order = 3)
  private int angle;
}
