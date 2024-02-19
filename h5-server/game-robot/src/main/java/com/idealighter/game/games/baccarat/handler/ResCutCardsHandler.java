
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.NOTICE_STAGE_CUT_CARD)

public class ResCutCardsHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResCutCardsHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResCutCardsMsg msg = (ResCutCardsMsg) message;

  }
}
