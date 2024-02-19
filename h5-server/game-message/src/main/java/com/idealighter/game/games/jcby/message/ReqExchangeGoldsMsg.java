/*
 * Copyright (c) 2015, http://www.wuleyou.com/ All Rights Reserved.
 * 
 *
 */
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;


@ReqMsg(ModuleMsgIdConstant.Jcby.EXCHANGE_GOLDS)
@Data
public class ReqExchangeGoldsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.EXCHANGE_GOLDS;
  // 兑换的筹码的金币数量
  @Protobuf(order = 2)
  private long chips;
}
