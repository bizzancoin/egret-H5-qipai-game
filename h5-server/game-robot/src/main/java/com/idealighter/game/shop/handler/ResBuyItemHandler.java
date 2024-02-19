
package com.idealighter.game.shop.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(103201)

public class ResBuyItemHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    // ResBuyItemMsg msg = (ResBuyItemMsg) message;

  }
}
