
package com.idealighter.game.core.service.games.ddz.handler;

import com.google.inject.Inject;
import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.ddz.manager.DdzMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.DDZ.ABANDON_PLAY_CARD)
public class ReqAbandonHandler implements ReqMessageHandler {

  @Inject
  private DdzMgr ddzMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    // ReqAbandonMsg msg = (ReqAbandonMsg) message;
    return ddzMgr.abandonPlayCards(player);
  }
}
