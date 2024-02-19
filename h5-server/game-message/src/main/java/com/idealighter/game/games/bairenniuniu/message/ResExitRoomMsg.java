
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家请求退出房间 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_EXIT_ROOM)
@Data
public class ResExitRoomMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_EXIT_ROOM;
}
