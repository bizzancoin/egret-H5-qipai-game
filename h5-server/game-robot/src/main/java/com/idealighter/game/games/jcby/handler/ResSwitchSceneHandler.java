
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import java.util.concurrent.TimeUnit;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_SWITCH_SCENCE)
public class ResSwitchSceneHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResSwitchSceneHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResSwitchSceneMsg msg = (ResSwitchSceneMsg)message;
    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    // int fireDelay = RandCodeUtil.random(7, 7);
    int fireDelay = 7;
    jcbyPlayer.pauseFire = true;

    jcbyPlayer.schedule(() -> {
      jcbyPlayer.pauseFire = false;
    }, fireDelay, TimeUnit.SECONDS);

  }
}
