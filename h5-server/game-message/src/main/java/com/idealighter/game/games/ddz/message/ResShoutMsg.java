
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家喊话 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_PLAYER_SHOUT)
@Data
public class ResShoutMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_PLAYER_SHOUT;
  // 座位顺序(0-2)
  @Protobuf(order = 2)
  private int order;
  @Protobuf(order = 3)
  private long playerId;
  // 0:嘲讽,2:催牌,3:赞扬
  @Protobuf(order = 4)
  private int type;
}
