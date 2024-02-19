
package com.idealighter.game.core.service.login.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Account.LOGOUT)
public class ReqLogoutHandler implements ReqMessageHandler {
  @Inject
  private LoginMgr loginMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    // ReqLogoutMsg msg = (ReqLogoutMsg)message;

    return loginMgr.resLogout(player);
  }
}
