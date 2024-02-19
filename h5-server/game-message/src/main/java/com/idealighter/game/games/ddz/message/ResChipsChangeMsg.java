
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家筹码变化消息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_CHIPS_CHANGE)
@Data
public class ResChipsChangeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_CHIPS_CHANGE;
  // 玩家ID
  @Protobuf(order = 2)
  private long playerId;
  @Protobuf(order = 3)
  private int order;
  // 兑换的游戏币后的筹码
  @Protobuf(order = 4)
  private long chips;
}
