
package com.idealighter.game.personalcenter.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 请求实名认证结果 .
 *
 */
@ResMsg(ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD)
@Data
public class ResModifyLoginPwdMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD;
  // 0:成功,1:旧密码输入不正确, 2:新密码格式不对
  @Protobuf(order = 2)
  private int res;
}
