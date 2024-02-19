package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;

import lombok.Data;

/**
 * 玩家退出消息.
 * 
 * @author messageGenerator
 *
 */
@ResMsg(ModuleMsgIdConstant.Account.NOTICE_REPETITION_LOGOUT)
@Data
public class ResLogoutForRepetitionMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.NOTICE_REPETITION_LOGOUT;
}
