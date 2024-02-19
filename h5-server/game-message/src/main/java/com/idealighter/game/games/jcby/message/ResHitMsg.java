
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.FishDieInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 子弹打死鱼 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_HIT)
@Data
public class ResHitMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_HIT;
  // 玩家id
  @Protobuf(order = 2)
  private long playerId;
  // 位置
  @Protobuf(order = 3)
  private int order;
  // 死亡的鱼
  @Protobuf(order = 4)
  private List<FishDieInfo> dies = new ArrayList<>();
}
