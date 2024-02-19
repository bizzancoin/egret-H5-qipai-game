
package com.idealighter.game.games.bairenniuniu.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家取消申请当庄家 .
 *
 */
@ReqMsg(ModuleMsgIdConstant.BaiRenNiuNiu.CANCEL_APPLY_BANKER)
@Data
public class ReqCancelApplyBankerMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.BaiRenNiuNiu.CANCEL_APPLY_BANKER;
}
