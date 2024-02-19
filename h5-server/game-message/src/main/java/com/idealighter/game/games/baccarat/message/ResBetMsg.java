
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 返回下注结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.BET)
@Data
public class ResBetMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.BET;
  // 下注区域 闲家1-11
  @Protobuf(order = 2)
  private int area;
  // 对应的筹码
  @Protobuf(order = 3)
  private long chips;
}
