
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家倍数通知 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_MULTIPLE)
@Data
public class ResMultipleMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_MULTIPLE;
  @Protobuf(order = 2)
  private int multiple; // 倍数
}
