
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家请求开炮 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.FIRE)
@Data
public class ReqFireMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.FIRE;
  // 夹角
  @Protobuf(order = 2)
  private int angle;
}
