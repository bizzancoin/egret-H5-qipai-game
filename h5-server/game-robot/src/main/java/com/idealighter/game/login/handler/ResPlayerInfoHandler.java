
package com.idealighter.game.login.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.personalcenter.message.ResPlayerInfoMsg;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

/**
 * 玩家信息.
 */
@ResMsgHandler(ModuleMsgIdConstant.Player.NOTICE_PLAYER_INFO)
public class ResPlayerInfoHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    ResPlayerInfoMsg msg = (ResPlayerInfoMsg) message;
    player.playerInfo = msg.getPlayerInfo();
  }
}
