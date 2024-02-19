
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 返回下注结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.BET)
@Data
public class ResBetMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.BET;
  // 下注区域 闲家1-4
  @Protobuf(order = 2)
  private int area;
  // 对应的筹码
  @Protobuf(order = 3)
  private long chips;
  
  // 闲家在庄家上下注的总金币
  @Protobuf(order = 4)
  private long bankerTotalBets;
}
