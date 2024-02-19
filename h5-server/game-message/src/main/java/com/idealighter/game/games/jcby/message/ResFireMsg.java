
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家请求开炮结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_FIRE)
@Data
public class ResFireMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_FIRE;
  // 发炮玩家
  @Protobuf(order = 2)
  private long playerId;
  // 位置
  @Protobuf(order = 3)
  private int order;
  // 子弹id
  @Protobuf(order = 4)
  private long bulletId;
  // 夹角
  @Protobuf(order = 5)
  private int angle;
}
