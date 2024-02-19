
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;


@Data
@ReqMsg(ModuleMsgIdConstant.Jcby.EXCHAGE_CHIPS)
public class ReqExchangeChipsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.EXCHAGE_CHIPS;
  
  // 兑换的筹码的金币数量
  @Protobuf(order = 2)
  private long gold;
}
