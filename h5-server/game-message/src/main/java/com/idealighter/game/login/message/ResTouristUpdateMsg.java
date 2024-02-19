
package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 游客升级结果.
 *
 */
@ResMsg(ModuleMsgIdConstant.Account.TOURIST_UPDATE)
@Data
public class ResTouristUpdateMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.TOURIST_UPDATE;
  // 昵称
  @Protobuf(order = 2)
  private String playerName;

  @Protobuf(order = 3)
  private String phone;
}
