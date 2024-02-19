
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.baccarat.message.ReqGameInfoMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.FASTER_ENTER_TABLE)

public class ResFastEnterTableHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResFastEnterTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    player.position = PlayerPosition.TABLE;
    
    ReqGameInfoMsg reqGameInfoMsg = new ReqGameInfoMsg();
    player.sendMsg(reqGameInfoMsg);

  }
}
