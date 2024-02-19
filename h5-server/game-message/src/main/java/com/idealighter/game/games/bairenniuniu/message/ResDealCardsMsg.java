
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.bairenniuniu.dto.CardsInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 发牌 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_DEAL_CARDS)
@Data
public class ResDealCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_DEAL_CARDS;
  // 牌信息
  @Protobuf(order = 2)
  private List<CardsInfo> cardsInfo = new ArrayList<>();
}
