
package com.idealighter.game.core.service.games.jcby.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.jcby.manager.JcbyMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.jcby.message.ReqFastEnterMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Jcby.FAST_ENTER)
public class ReqFastEnterHandler implements ReqMessageHandler {

  @Inject
  private JcbyMgr jcbyMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqFastEnterMsg msg = (ReqFastEnterMsg) message;
    return jcbyMgr.fastEnter(player, msg.getRoomId());
  }
}
