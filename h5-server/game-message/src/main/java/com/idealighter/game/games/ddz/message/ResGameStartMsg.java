
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 游戏开始 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_GAME_START)
@Data
public class ResGameStartMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_GAME_START;
  // 首先叫牌的座位顺序号(0-2)
  @Protobuf(order = 2)
  private int firstCallOrder;
  // 首先叫牌的座位玩家编号
  @Protobuf(order = 3)
  private long firstCallPlayerId;
  // 首次叫牌的时间限制
  @Protobuf(order = 4)
  private int firstCallTimeLimit;
}
