
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.message.ResExitTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_EXIT_TABLE)
public class ResExitTableHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResExitTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResExitTableMsg msg = (ResExitTableMsg) message;
    if (msg.getPlayerId() == player.playerId) {
      JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
      jcbyPlayer.stopFire();
    }
  }
}
