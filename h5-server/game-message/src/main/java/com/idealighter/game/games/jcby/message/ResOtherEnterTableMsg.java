
package com.idealighter.game.games.jcby.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 其他人进入桌子 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Jcby.RES_OTHER_ENTER_TABLE)
@Data
public class ResOtherEnterTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Jcby.RES_OTHER_ENTER_TABLE;
  // 炮台
  @Protobuf(order = 2)
  private BatteryInfo battery;
  // 进入桌子的其他玩家
  @Protobuf(order = 3)
  private com.idealighter.game.gamehall.dto.MemInfo mem;
}
