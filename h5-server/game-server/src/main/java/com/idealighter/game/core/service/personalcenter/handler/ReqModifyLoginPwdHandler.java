
package com.idealighter.game.core.service.personalcenter.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.personalcenter.manager.PersonalCenterMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.personalcenter.message.ReqModifyLoginPwdMsg;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD)
public class ReqModifyLoginPwdHandler implements ReqMessageHandler {

  @Inject
  private PersonalCenterMgr personalCenterMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqModifyLoginPwdMsg msg = (ReqModifyLoginPwdMsg) message;
    return personalCenterMgr.modifyLoginPwd(player, msg.getOldPwd(), msg.getNewPwd());

  }
}
