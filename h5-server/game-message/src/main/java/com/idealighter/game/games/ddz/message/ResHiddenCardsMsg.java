
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 底牌 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_HIDDEN_CARDS)
@Data
public class ResHiddenCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_HIDDEN_CARDS;
  @Protobuf(order = 2)
  private int multipleTime; // 加倍时间
  // 底牌
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  private List<Integer> cards = new ArrayList<>();
}
