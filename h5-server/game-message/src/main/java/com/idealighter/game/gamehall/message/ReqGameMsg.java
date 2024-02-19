
package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 游客账号升级.
 */
@ReqMsg(ModuleMsgIdConstant.Game.GAME_LIST)
@Data
public class ReqGameMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.GAME_LIST;
}
