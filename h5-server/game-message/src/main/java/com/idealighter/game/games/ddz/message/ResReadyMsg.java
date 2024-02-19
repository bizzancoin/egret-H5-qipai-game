
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 游戏进入准备阶段 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_READY)
@Data
public class ResReadyMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_READY;
  @Protobuf(order = 2)
  private long playerId;
  @Protobuf(order = 3)
  private int order;
}
