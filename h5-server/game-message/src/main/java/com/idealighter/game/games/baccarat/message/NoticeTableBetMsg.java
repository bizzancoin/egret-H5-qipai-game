
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.games.baccarat.dto.BetInfo;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.ModuleMsgIdConstant.Baccarat;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 桌子筹码变化 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_TABLE_BET)
@Data
public class NoticeTableBetMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = Baccarat.NOTICE_TABLE_BET;
  // 桌上筹码信息
  @Protobuf(order = 2)
  private List<BetInfo> betInfo = new ArrayList<>();
}
