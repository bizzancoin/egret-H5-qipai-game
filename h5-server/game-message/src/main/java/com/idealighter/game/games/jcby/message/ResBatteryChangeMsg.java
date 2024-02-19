
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家炮台改变消息 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_BATTERY_CHANGE)
@Data
public class ResBatteryChangeMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_BATTERY_CHANGE;
  // 炮台
  @Protobuf(order = 2)
  private BatteryInfo battery;
}
