
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 切牌信息(切出来的12张牌) .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_CUT_CARD)
@Data
public class NoticeCutCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_CUT_CARD;
  // 牌信息
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> cardsInfo = new ArrayList<>();
}
