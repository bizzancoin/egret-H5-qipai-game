
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
 * 发牌 .
 *
 */
@ResMsg(ModuleMsgIdConstant.DDZ.RES_DEAL_CARDS)
@Data
public class ResDealCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.RES_DEAL_CARDS;
  // 玩家的牌
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> cards = new ArrayList<>();
}
