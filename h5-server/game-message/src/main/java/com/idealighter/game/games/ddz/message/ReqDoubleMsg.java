
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家请求加倍 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.DOUBLE)
@Data
public class ReqDoubleMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.DOUBLE;
  // 0:不加倍,非0:加倍
  @Protobuf(order = 2)
  private int doubled;
}
