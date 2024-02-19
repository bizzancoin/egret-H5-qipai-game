
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 进入牌桌结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_OTHER_ENTER_TABLE)
@Data
public class ResOtherEnterTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_OTHER_ENTER_TABLE;
  // 进入桌子的其他玩家
  @Protobuf(order = 2)
  private com.idealighter.game.gamehall.dto.MemInfo mem;
}
