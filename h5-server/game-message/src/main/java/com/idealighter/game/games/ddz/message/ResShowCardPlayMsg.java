
package com.idealighter.game.games.ddz.message;

import java.util.ArrayList;
import java.util.List;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;

/**
 * 游戏进入准备阶段 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_SHOW_CARD_PLAY)
@Data
public class ResShowCardPlayMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_SHOW_CARD_PLAY;
  @Protobuf(order = 2)
  private long playerId; // 明牌的玩家
  @Protobuf(order = 3)
  private int order; // 明牌座位编号
}
