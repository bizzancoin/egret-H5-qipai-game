
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.NOTICE_TABLE_BET)

public class NoticeTableBetHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResTableBetHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResTableBetMsg msg = (ResTableBetMsg) message;

  }
}
