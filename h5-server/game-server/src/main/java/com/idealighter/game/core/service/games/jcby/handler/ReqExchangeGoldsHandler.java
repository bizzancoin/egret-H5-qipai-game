package com.idealighter.game.core.service.games.jcby.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.jcby.manager.JcbyMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.jcby.message.ReqExchangeGoldsMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Jcby.EXCHANGE_GOLDS)
public class ReqExchangeGoldsHandler implements ReqMessageHandler {

  @Inject
  private JcbyMgr jcbyMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqExchangeGoldsMsg msg = (ReqExchangeGoldsMsg) message;
    return jcbyMgr.exchangeGold(player, msg.getChips());
  }
}
