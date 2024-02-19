
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家下注 .
 */
@Data
@ReqMsg(ModuleMsgIdConstant.Baccarat.BET)
public class ReqBetMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.BET;
  // 下注区域 闲家0-10
  @Protobuf(order = 2)
  private int area;
  // 对应的筹码
  @Protobuf(order = 3)
  private long chips;
}
