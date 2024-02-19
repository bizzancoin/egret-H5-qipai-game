
package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 房间成员变更信息.
 */
@ResMsg(ModuleMsgIdConstant.Game.NOTICE_MEM_INFO_UPDATE)
@Data
public class ResMemInfoUpdateMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.NOTICE_MEM_INFO_UPDATE;
  // 房间成员信息
  @Protobuf(order = 2)
  private MemInfo member;
}
