
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 进入牌桌结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_ENTER_TABLE)
@Data
public class ResEnterTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_ENTER_TABLE;
  // 牌桌中的玩家信息
  @Protobuf(order = 2)
  private List<com.idealighter.game.gamehall.dto.MemInfo> mems = new ArrayList<>();
  @Protobuf(order = 3)
  private long totalChips;
  @Protobuf(order = 4)
  private int readyTime; // 进入桌子后准备的时间
}
