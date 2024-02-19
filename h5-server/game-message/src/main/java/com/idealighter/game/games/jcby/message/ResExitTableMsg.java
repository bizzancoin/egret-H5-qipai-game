
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家请求退出房间牌桌结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_EXIT_TABLE)
@Data
public class ResExitTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_EXIT_TABLE;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
  // 座位顺序
  @Protobuf(order = 3)
  private int order;
}
