
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.baccarat.message.ReqGameInfoMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.ENTER_TABLE)
public class ResEnterTableHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResEnterTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    player.position = PlayerPosition.TABLE;
    LOG.info("[百家乐]玩家[{}][{}]进入桌子[{}]成功", player.userName, player.playerId,
        player.table(player.playerId).getId());

    ReqGameInfoMsg reqGameInfoMsg = new ReqGameInfoMsg();
    player.sendMsg(reqGameInfoMsg);

  }
}
