
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_HIT)

public class ResHitHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResHitHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResHitMsg msg = (ResHitMsg) message;

  }
}
