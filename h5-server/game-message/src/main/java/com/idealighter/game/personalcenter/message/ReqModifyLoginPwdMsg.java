
package com.idealighter.game.personalcenter.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 请求修改登录密码 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD)
@Data
public class ReqModifyLoginPwdMsg implements ReqMessage {
  // 旧密码
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD;
  @Protobuf(order = 2)
  private String oldPwd;
  // 新密码
  @Protobuf(order = 3)
  private String newPwd;
}
