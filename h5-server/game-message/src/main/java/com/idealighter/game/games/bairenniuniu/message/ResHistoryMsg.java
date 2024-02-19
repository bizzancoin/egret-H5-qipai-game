
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 历史输赢记录 .
 *
 */
@ResMsg(ModuleMsgIdConstant.BaiRenNiuNiu.RES_HISTORY)
@Data
public class ResHistoryMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.RES_HISTORY;
  // 闲家对庄家的输赢记录
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> result = new ArrayList<>();
}
