
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求进入大厅 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.ENTER_GAME_HALL)
@Data
public class ReqEnterGameHallMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.ENTER_GAME_HALL;
}
