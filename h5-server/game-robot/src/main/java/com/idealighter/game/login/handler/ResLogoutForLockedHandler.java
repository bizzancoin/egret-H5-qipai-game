
package com.idealighter.game.login.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Account.NOTICE_LOCKED_LOGOUT)
public class ResLogoutForLockedHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    // ResLogoutMsg msg = (ResLogoutMsg)message;
    player.close();
  }
}
