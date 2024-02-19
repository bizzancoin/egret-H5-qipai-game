
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家请求提示牌 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.PROMPT)
@Data
public class ReqPromptMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.PROMPT;
}
