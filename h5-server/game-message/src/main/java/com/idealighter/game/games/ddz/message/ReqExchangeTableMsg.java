
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家请求换桌.
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.EXCHANGE_TABLE)
@Data
public class ReqExchangeTableMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.EXCHANGE_TABLE;
}
