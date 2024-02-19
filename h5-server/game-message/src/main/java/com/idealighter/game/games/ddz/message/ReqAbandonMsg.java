
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家请求放弃(不要)出牌 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.ABANDON_PLAY_CARD)
@Data
public class ReqAbandonMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.ABANDON_PLAY_CARD;
}
