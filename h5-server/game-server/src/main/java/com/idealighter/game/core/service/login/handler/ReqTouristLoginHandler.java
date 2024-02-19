
package com.idealighter.game.core.service.login.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.login.message.ReqTouristLoginMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Account.TOURIST_LOGIN)
public class ReqTouristLoginHandler implements ReqMessageHandler {

  @Inject
  private LoginMgr loginMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqTouristLoginMsg msg = (ReqTouristLoginMsg) message;
    return loginMgr.touristLogin(player, msg.getCode(), msg.getPlatform());
  }
}
