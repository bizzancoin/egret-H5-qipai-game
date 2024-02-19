
package com.idealighter.game.core.service.login.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.login.message.ReqPhoneLoginOrRegisterMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Account.PHONE_LOGIN_OR_REGISTER)
public class ReqPhoneLogionOrRegisterHandler implements ReqMessageHandler {
  @Inject
  private LoginMgr loginMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqPhoneLoginOrRegisterMsg msg = (ReqPhoneLoginOrRegisterMsg) message;
    return loginMgr.phoneLoginOrRegister(player, msg.getPhone(), msg.getSmsCode(),
        msg.getPlatform());
  }
}
