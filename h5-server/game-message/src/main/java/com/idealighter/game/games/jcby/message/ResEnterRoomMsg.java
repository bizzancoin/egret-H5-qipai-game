
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 进入房间结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.ENTER_ROOM)
@Data
public class ResEnterRoomMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.ENTER_ROOM;
  // 牌桌信息
  @Protobuf(order = 2)
  private List<com.idealighter.game.gamehall.dto.TableInfo> tables = new ArrayList<>();
  // 房间座位信息
  @Protobuf(order = 3)
  private List<com.idealighter.game.gamehall.dto.MemInfo> members = new ArrayList<>();
}
