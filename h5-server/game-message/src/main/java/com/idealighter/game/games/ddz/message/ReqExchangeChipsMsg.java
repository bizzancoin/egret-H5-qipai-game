
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求兑换游戏筹码 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.EXCHAGE_CHIPS)
@Data
public class ReqExchangeChipsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.EXCHAGE_CHIPS;
  // 兑换的筹码的金币数量
  @Protobuf(order = 2)
  private long gold;
}
