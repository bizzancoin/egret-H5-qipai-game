package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 手机登录获取验证码.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.Account.PHONE_LOGIN_OR_REGISTER_GET_SMS)
@Data
public class ReqPhoneLoginOrRegisterGetSmsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.PHONE_LOGIN_OR_REGISTER_GET_SMS;
  // 玩家手机
  @Protobuf(order = 2)
  private String phone;
}
