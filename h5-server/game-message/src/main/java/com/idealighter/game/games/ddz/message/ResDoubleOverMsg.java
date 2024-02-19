
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 加倍结束通知 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_DOUBLE_OVER)
@Data
public class ResDoubleOverMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_DOUBLE_OVER;
  // 下一个出牌玩家的顺序
  @Protobuf(order = 2)
  private int nextPlayOrder;
  // 下一个出牌玩家编号
  @Protobuf(order = 3)
  private long nextPlayPlayerId;
  // 下一个出牌玩家出牌时间
  @Protobuf(order = 4)
  private int nextPlayTimeLimit;
}
