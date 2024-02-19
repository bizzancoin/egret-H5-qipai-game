
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求进入牛牛大厅 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.BaiRenNiuNiu.ENTER_GAME_HALL)
@Data
public class ReqEnterGameHallMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.ENTER_GAME_HALL;
}
