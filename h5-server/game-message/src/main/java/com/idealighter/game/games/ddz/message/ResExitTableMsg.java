
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 游戏结束(后面会有结算等信息) .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_EXIT_TABLE)
@Data
public class ResExitTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_EXIT_TABLE;
  // 座位顺序
  @Protobuf(order = 2)
  private int order;
  // 退桌玩家id
  @Protobuf(order = 3)
  private long playerId;
}
