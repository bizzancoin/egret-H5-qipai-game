
package com.idealighter.game.games.ddz.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 明牌 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.DDZ.SHOW_CARD_PLAY)
@Data
public class ReqShowCardPlayMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.DDZ.SHOW_CARD_PLAY;
  @Protobuf(order = 2)
  private int type; // 类型(0: 发牌明牌(5倍),1: 发少量牌明牌(4倍), 2: 剩余三张牌明牌(3倍), 3:发完牌后明牌(2倍))
}
