
package com.idealighter.game.bank.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(107201)

public class ResTransferGoldSuccessHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResTransferGoldSuccessHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResTransferGoldSuccessMsg msg = (ResTransferGoldSuccessMsg) message;

  }
}
