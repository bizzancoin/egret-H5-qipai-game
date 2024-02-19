
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.baccarat.dto.CardsInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 发牌 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_DEAL_CARD)
@Data
public class NoticeDealCardsMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_DEAL_CARD;
  // 牌信息
  @Protobuf(order = 2)
  private List<CardsInfo> cardsInfo = new ArrayList<>();
  // 牌序(发到第多少张牌了)
  @Protobuf(order = 3)
  private int cardIndex;
  // 上一局剩余多少牌 (总共六张,发剩余了多少)
  @Protobuf(order = 4)
  private int leftNum;
  // 是否发到了黄牌(1是0否)
  @Protobuf(order = 5)
  private int yellowCard;
}
