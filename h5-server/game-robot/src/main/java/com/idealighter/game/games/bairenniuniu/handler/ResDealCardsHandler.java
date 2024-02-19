
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_DEAL_CARDS)
public class ResDealCardsHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResDealCardsHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResDealCardsMsg msg = (ResDealCardsMsg) message;

  }
}
