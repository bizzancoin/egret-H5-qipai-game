
package com.idealighter.game.games.ddz.message;

import java.util.ArrayList;
import java.util.List;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.ddz.dto.RoomTypeDetailInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 进入小游戏大厅结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.ENTER_GAME_HALL)
@Data
public class ResEnterGameHallMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.ENTER_GAME_HALL;
  // 房间类型信息
  @Protobuf(order = 2)
  private List<RoomTypeDetailInfo> roomTypes = new ArrayList<>();
}
