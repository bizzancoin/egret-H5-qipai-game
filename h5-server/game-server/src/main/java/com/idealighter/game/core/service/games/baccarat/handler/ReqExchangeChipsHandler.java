package com.idealighter.game.core.service.games.baccarat.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.baccarat.manager.BaccaratMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.baccarat.message.ReqExchangeChipsMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Baccarat.EXCHAGE_CHIPS)
public class ReqExchangeChipsHandler implements ReqMessageHandler {
  @Inject
  private BaccaratMgr baccMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqExchangeChipsMsg msg = (ReqExchangeChipsMsg) message;
    return baccMgr.exchangeChips(player, msg.getGold());
  }
}
