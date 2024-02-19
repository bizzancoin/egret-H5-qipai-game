
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家切换炮台 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Jcby.SWITCH_BATTERY)
@Data
public class ReqSwitchBatteryMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.SWITCH_BATTERY;
  // 类型(1:加炮,非1:减炮)
  @Protobuf(order = 2)
  private int type;
}
