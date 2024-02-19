
package com.idealighter.game.login.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.HEART_BEAT)

public class ResHeartbeatHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResHeartbeatHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResHeartbeatMsg msg = (ResHeartbeatMsg) message;

  }
}
