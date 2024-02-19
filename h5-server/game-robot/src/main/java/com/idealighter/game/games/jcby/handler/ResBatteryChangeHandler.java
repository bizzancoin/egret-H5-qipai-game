
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.message.ResBatteryChangeMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_BATTERY_CHANGE)
public class ResBatteryChangeHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResBatteryChangeHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResBatteryChangeMsg msg = (ResBatteryChangeMsg) message;
    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    BatteryInfo battery = msg.getBattery();
    if (battery.getPlayerId() == jcbyPlayer.playerId) {
      jcbyPlayer.battery = msg.getBattery();
    }
  }
}
