
package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.gamehall.dto.SeatInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 房间座位变更信息.
 */
@ResMsg(ModuleMsgIdConstant.Game.NOTICE_SEAT_INFO_UPDATE)
@Data
public class ResSeatInfoUpdateMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Game.NOTICE_SEAT_INFO_UPDATE;
  // 房间座位信息
  @Protobuf(order = 2)
  private SeatInfo seat;
}
