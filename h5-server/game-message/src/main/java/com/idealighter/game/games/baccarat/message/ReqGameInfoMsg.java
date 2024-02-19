package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

@ReqMsg(ModuleMsgIdConstant.Baccarat.GAME_INFO)
@Data
public class ReqGameInfoMsg implements ReqMessage {

  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.GAME_INFO;
}
