
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 玩家放弃(不要)结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_ABANDON_PLAY_CARD)
@Data
public class ResAbandonMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_ABANDON_PLAY_CARD;
  // 放弃出牌的玩家位置
  @Protobuf(order = 2)
  private int order;
  @Protobuf(order = 3)
  private long playerId;
  // 下一个出牌玩家的顺序
  @Protobuf(order = 4)
  private int nextPlayOrder;
  // 下一个出牌玩家编号
  @Protobuf(order = 5)
  private long nextPlayPlayerId;
  // 下一个出牌玩家出牌时间
  @Protobuf(order = 6)
  private int nextPlayTimeLimit;
}
