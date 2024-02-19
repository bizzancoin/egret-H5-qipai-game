
package com.idealighter.game.gamehall.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Account.TOURIST_UPDATE)

public class ResTouristUpdateHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResTouristUpdateHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResTouristUpdateMsg msg = (ResTouristUpdateMsg) message;

  }
}
