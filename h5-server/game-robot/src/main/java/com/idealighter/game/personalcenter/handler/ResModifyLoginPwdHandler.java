
package com.idealighter.game.personalcenter.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Player.MODIFY_LOGIN_PWD)

public class ResModifyLoginPwdHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResModifyLoginPwdHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResModifyLoginPwdMsg msg = (ResModifyLoginPwdMsg) message;

  }
}
