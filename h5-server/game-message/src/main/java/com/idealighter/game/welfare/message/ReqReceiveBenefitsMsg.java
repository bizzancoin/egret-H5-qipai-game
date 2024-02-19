
package com.idealighter.game.welfare.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 玩家领取救济金.
 *
 */
@ReqMsg(ModuleMsgIdConstant.Welfare.RECEIVE_BENEFITS)
@Data
public class ReqReceiveBenefitsMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Welfare.RECEIVE_BENEFITS;
}
