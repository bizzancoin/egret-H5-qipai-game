
package com.idealighter.game.common.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.DEFAULT_RES)
public class ResDefaultMsgHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    // NOTICE_ERROR ResErrorMsg msg = (ResErrorMsg) message;

  }
}
