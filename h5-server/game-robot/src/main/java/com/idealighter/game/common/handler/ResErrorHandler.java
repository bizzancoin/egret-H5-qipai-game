
package com.idealighter.game.common.handler;

import com.idealighter.game.common.message.ResErrorMsg;
import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.error.ErrorCodeMap;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.NOTICE_ERROR)
public class ResErrorHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    ResErrorMsg msg = (ResErrorMsg) message;
    ErrorCode errorCode = ErrorCodeMap.getById(msg.getErrorCode());
    switch (errorCode) {
      case GAME_BELOW_MIN_GOLD:
      case GAME_BEYOND_MAX_GOLD:
      case GAME_ROOM_CLOSED:
        player.close();
        break;

      default:
        break;
    }


  }
}
