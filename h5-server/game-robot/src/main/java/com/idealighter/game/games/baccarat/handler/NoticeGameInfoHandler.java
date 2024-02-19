package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.baccarat.message.ResGameInfoMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.GAME_INFO)
public class NoticeGameInfoHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(NoticeGameInfoHandler.class);

  @Override
  public void action(Player player, ResMessage msg) {
    ResGameInfoMsg gameInfoMsg = (ResGameInfoMsg) msg;
    LOG.info("[百家乐] 获取 {} 游戏信息", gameInfoMsg.getRoomInfo().getName());

  }

}
