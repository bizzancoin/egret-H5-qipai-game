
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家叫地主 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.CALL_CARD)
@Data
public class ReqCallCardMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.CALL_CARD;
  // 0:不叫地主,1:叫地主,2:不抢地主,3:抢地主
  @Protobuf(order = 2)
  private int type;
}
