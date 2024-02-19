
package com.idealighter.game.games.baccarat.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 历史路单记录 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_HISTORY)
@Data
public class NoticeHistoryMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_HISTORY;
  // 历史路单记录
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> hisWaybill = new ArrayList<>();
  // 历史龙虎记录
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  private List<Integer> hisDragon = new ArrayList<>();
  // 历史大小记录
  @Protobuf(fieldType = FieldType.INT32, order = 4)
  private List<Integer> hisBigSmall = new ArrayList<>();
}
