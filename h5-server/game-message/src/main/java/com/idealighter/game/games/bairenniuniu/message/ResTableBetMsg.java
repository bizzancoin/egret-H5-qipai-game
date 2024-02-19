
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.bairenniuniu.dto.BetInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 桌子筹码变化 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_TABLE_BET)
@Data
public class ResTableBetMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_TABLE_BET;
  // 桌上筹码信息
  @Protobuf(order = 2)
  private List<BetInfo> betInfo = new ArrayList<>();
}
