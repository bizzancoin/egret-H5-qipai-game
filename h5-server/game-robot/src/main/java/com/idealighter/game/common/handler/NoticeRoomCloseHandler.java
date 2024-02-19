
package com.idealighter.game.common.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Game.NOTICE_ROOM_CLOSE_KICKOUT)
public class NoticeRoomCloseHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(NoticeRoomCloseHandler.class);

  @Override
  public void action(Player player, ResMessage message) {

    LOG.info("房间关闭 玩家退出 ", player.userName);

    player.position = PlayerPosition.PLAZA;
    player.clear();
    player.close();


  }
}
