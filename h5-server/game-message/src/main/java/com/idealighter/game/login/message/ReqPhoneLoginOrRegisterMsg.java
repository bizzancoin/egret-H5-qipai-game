package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 手机号码+短信登录.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.Account.PHONE_LOGIN_OR_REGISTER)
@Data
public class ReqPhoneLoginOrRegisterMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.PHONE_LOGIN_OR_REGISTER;
  // 短信码
  @Protobuf(order = 2)
  private String smsCode;
  @Protobuf(order = 3)
  private String phone;
  @Protobuf(order = 4)
  private int platform = 0; // 平台(0:ios,1:android,2:pc)
}
