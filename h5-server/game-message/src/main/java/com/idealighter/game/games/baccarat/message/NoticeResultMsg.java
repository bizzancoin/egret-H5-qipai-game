
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
 * 开出的结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Baccarat.NOTICE_RESULT)
@Data
public class NoticeResultMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Baccarat.NOTICE_RESULT;
  // 开出结果列表
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  private List<Integer> result = new ArrayList<>();
}
