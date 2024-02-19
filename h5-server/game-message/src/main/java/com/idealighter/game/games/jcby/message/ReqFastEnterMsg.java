
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求进入房间 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.FAST_ENTER)
@Data
public class ReqFastEnterMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.FAST_ENTER;
  // 房间id
  @Protobuf(order = 2)
  private int roomId;
}
