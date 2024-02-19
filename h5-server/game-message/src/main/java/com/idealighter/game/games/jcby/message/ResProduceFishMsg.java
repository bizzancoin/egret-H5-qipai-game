
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.FishInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 产生鱼消息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_PRODUCE_FISH)
@Data
public class ResProduceFishMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_PRODUCE_FISH;
  // 鱼
  @Protobuf(order = 2)
  private List<FishInfo> fishs = new ArrayList<>();
}
