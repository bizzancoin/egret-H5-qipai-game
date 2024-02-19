
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.bairenniuniu.message.ResBankerInfoMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_BANKER_INFO)
public class ResBankerInfoHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResBankerInfoHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResBankerInfoMsg msg = (ResBankerInfoMsg) message;
    // 记录庄家编号
    player.bankerId = msg.getPlayerId();
  }
}
