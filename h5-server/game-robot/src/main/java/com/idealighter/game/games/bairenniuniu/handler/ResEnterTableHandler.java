
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_ENTER_TABLE)
public class ResEnterTableHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResEnterTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    player.position = PlayerPosition.TABLE;
    LOG.info("[百人牛牛]玩家[{}][{}]进入桌子成功", player.userName, player.playerId);
  }
}
