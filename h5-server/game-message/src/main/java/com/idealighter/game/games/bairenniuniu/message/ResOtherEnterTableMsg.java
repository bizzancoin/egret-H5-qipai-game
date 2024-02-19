
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 其他人进入桌子 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_OTHER_ENTER_TABLE)
@Data
public class ResOtherEnterTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_OTHER_ENTER_TABLE;
  // 进入桌子的其他玩家
  @Protobuf(order = 2)
  private com.idealighter.game.gamehall.dto.MemInfo mem;
}
