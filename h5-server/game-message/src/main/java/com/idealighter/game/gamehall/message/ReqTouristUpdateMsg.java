
package com.idealighter.game.gamehall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 游客账号升级.
 */
@ReqMsg(ModuleMsgIdConstant.Account.TOURIST_UPDATE)
@Data
public class ReqTouristUpdateMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.TOURIST_UPDATE;
  // 手机验证码
  @Protobuf(order = 2)
  private String smsCode;
  // 昵称
  @Protobuf(order = 3)
  private String playerName;
  // 推荐人
  @Protobuf(order = 4)
  private long recommend;
}
