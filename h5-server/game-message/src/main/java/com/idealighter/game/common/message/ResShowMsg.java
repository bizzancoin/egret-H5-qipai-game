
package com.idealighter.game.common.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 通用右下角展示消息.
 */
@ResMsg(ModuleMsgIdConstant.NOTICE_SHOW)
@Data
public class ResShowMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.NOTICE_SHOW;
  // 消息id
  @Protobuf(order = 2)
  private int msgId;
  // 系统(暂时未用)
  @Protobuf(order = 3)
  private int system;
  // 消息参数
  @Protobuf(fieldType = FieldType.STRING, order = 4)
  private List<String> args = new ArrayList<>();
}
