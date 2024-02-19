
package com.idealighter.game.welfare.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家签到.
 *
 */
@ReqMsg(ModuleMsgIdConstant.Welfare.SIGN_IN)
@Data
public class ReqSignInMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Welfare.SIGN_IN;
}
