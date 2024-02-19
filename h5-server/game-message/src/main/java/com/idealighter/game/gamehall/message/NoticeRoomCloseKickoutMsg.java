package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

@ResMsg(ModuleMsgIdConstant.Game.NOTICE_ROOM_CLOSE_KICKOUT)
@Data
public class NoticeRoomCloseKickoutMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.NOTICE_ROOM_CLOSE_KICKOUT;
}
