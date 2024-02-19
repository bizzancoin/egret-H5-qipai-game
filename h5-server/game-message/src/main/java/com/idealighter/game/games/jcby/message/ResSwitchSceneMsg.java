
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 切换场景 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_SWITCH_SCENCE)
@Data
public class ResSwitchSceneMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_SWITCH_SCENCE;
  // 场景id
  @Protobuf(order = 2)
  private int scene;
}
