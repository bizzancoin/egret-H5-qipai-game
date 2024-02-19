
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 玩家请求快速进入房间牌桌结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.FASTER_ENTER_TABLE)
@Data
public class ResFastEnterTableMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.FASTER_ENTER_TABLE;
  
  @Protobuf(order = 2)
  private List<com.idealighter.game.gamehall.dto.MemInfo> mems = new ArrayList<>();

}
