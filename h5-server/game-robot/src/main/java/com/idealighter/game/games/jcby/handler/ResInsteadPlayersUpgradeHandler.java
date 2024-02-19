
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_INSTEAD_PLAYER_UPGRADE)

public class ResInsteadPlayersUpgradeHandler implements ResMessageHandler {

  // private static final Logger LOG =
  // LoggerFactory.getLogger(ResInsteadPlayersUpgradeHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResInsteadPlayersUpgradeMsg msg = (ResInsteadPlayersUpgradeMsg) message;

  }
}
