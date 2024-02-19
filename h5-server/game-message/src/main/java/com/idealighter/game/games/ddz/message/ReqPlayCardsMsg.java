
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家出牌 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.PLAY_CARD)
@Data
public class ReqPlayCardsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.PLAY_CARD;
  // 玩家打出的牌
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> cards = new ArrayList<>();
}
