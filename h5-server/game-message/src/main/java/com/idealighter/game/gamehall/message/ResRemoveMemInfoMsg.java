
package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 删除房间成员信息.
 */
@ResMsg(ModuleMsgIdConstant.Game.NOTICE_REMOVE_MEM_INFO)
@Data
public class ResRemoveMemInfoMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.NOTICE_REMOVE_MEM_INFO;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
}
