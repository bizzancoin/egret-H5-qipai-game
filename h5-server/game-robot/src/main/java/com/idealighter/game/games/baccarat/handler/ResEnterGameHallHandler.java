
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.baccarat.dto.RoomInfo;
import com.idealighter.game.games.baccarat.dto.RoomTypeDetailInfo;
import com.idealighter.game.games.baccarat.message.ResEnterGameHallMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.ENTER_GAME_HALL)
public class ResEnterGameHallHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResEnterGameHallHandler.class);

  @Override
  public void action(Player player, ResMessage message) {

    LOG.info("[百家乐]玩家[{}]进入大厅成功", player.userName);
    Set<Integer> rooms = player.rooms;
    player.position = PlayerPosition.HALL;

    rooms.clear();

    ResEnterGameHallMsg msg = (ResEnterGameHallMsg) message;

    if (msg.getRoomTypes() == null || msg.getRoomTypes().isEmpty()) {
      player.close();
      return;
    }

    for (RoomTypeDetailInfo roomTypeDetailInfo : msg.getRoomTypes()) {
      for (RoomInfo roomInfo : roomTypeDetailInfo.getRooms()) {
        rooms.add(roomInfo.getRoomId());
      }
    }

  }
}
