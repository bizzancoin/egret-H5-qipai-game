
package com.idealighter.game.gamehall.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.gamehall.message.ResRemoveMemInfoMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Game.NOTICE_REMOVE_MEM_INFO)
public class ResRemoveMemInfoHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResRemoveMemInfoHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResRemoveMemInfoMsg msg = (ResRemoveMemInfoMsg) message;
    player.removeMemInfo(msg.getPlayerId());
  }
}
