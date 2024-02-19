
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 玩家 . 喊话.
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.PLAYER_SHOUT)
@Data
public class ReqShoutMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.PLAYER_SHOUT;
  // 1:嘲讽,2:催牌,3:赞扬
  @Protobuf(order = 2)
  private int type;
}
