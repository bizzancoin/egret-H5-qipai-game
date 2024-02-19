
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_CHIPS_CHANGE)
public class ResChipsChangeHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResChipsChangeHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResChipsChangeMsg msg = (ResChipsChangeMsg) message;

  }
}
