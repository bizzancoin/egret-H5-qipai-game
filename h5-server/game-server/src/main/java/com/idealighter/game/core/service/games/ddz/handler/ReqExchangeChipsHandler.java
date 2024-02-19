
package com.idealighter.game.core.service.games.ddz.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuMgr;
import com.idealighter.game.core.service.games.ddz.manager.DdzMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.ddz.message.ReqExchangeChipsMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.DDZ.EXCHAGE_CHIPS)
public class ReqExchangeChipsHandler implements ReqMessageHandler {

  @Inject
  private DdzMgr ddzMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqExchangeChipsMsg msg = (ReqExchangeChipsMsg) message;
    return ddzMgr.exchangeChips(player, msg.getGold());
  }
}
