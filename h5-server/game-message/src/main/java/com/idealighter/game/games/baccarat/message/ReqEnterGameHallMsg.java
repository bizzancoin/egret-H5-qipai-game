
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求进入百家乐大厅 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Baccarat.ENTER_GAME_HALL)
@Data
public class ReqEnterGameHallMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.ENTER_GAME_HALL;
}
