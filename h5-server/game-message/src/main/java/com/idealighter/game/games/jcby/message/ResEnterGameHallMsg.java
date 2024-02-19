
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.RoomTypeDetailInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 进入游戏大厅返回房间数据 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.ENTER_GAME_HALL)
@Data
public class ResEnterGameHallMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.ENTER_GAME_HALL;
  // 房间
  @Protobuf(order = 2)
  private List<RoomTypeDetailInfo> roomTypes = new ArrayList<>();
}
