
package com.idealighter.game.gamehall.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.gamehall.message.ResMemInfoUpdateMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Game.NOTICE_MEM_INFO_UPDATE)
public class ResMemInfoUpdateHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResMemInfoUpdateHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResMemInfoUpdateMsg msg = (ResMemInfoUpdateMsg) message;
    player.memInfoUpdate(msg.getMember());
  }
}
