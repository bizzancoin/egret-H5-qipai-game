
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 李逵升级 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_LIKUI_UPGRADE)
@Data
public class ResLikuiUpgradeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_LIKUI_UPGRADE;
  // 李逵的鱼id
  @Protobuf(order = 2)
  private int fishId;
  // 升级后的倍率
  @Protobuf(order = 3)
  private int rate;
}
