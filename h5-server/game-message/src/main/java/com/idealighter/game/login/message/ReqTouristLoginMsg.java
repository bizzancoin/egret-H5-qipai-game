package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 游客登录.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.Account.TOURIST_LOGIN)
@Data
public class ReqTouristLoginMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.TOURIST_LOGIN;
  // 游客唯一标识
  @Protobuf(order = 2)
  private String code;
  @Protobuf(order = 3)
  private int platform = 0; // 平台(0:ios,1:android,2:pc)
}
