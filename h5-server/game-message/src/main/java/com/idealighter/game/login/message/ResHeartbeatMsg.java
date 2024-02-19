package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 心跳返回消息.
 * 
 * @author messageGenerator
 *
 */
@ResMsg(ModuleMsgIdConstant.HEART_BEAT)
@Data
public class ResHeartbeatMsg implements ResMessage {
  @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
  private int id = ModuleMsgIdConstant.HEART_BEAT;
}
