
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 返回结算结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_BILL)
@Data
public class NoticeBillMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_BILL;
  // 庄家结算筹码
  @Protobuf(order = 2)
  private long bankerChips;
  // 玩家结算筹码
  @Protobuf(order = 3)
  private long playerChips;
}
