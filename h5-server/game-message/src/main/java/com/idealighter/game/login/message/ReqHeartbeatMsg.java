package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 心跳消息.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.HEART_BEAT)
@Data
public class ReqHeartbeatMsg implements ReqMessage {
  @Protobuf(fieldType = FieldType.INT32, order = 1, required = true)
  private int id = ModuleMsgIdConstant.HEART_BEAT;
}
