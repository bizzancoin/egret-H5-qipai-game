
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.bairenniuniu.message.ResExitTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_EXIT_TABLE)
public class ResExitTableHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResExitTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResExitTableMsg msg = (ResExitTableMsg) message;
    if (msg.getPlayerId() == player.playerId) {
      player.position = PlayerPosition.ROOM;
      LOG.info("[百人牛牛]机器人[{}][{}]退出桌子成功", player.userName, player.playerId);
    }
  }

}
