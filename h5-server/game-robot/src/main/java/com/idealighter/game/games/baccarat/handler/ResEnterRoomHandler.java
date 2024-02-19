
package com.idealighter.game.games.baccarat.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.gamehall.dto.TableInfo;
import com.idealighter.game.games.baccarat.message.ResEnterRoomMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.ENTER_ROOM)
public class ResEnterRoomHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResEnterRoomHandler.class);

  @Override
  public void action(Player player, ResMessage message) {

    player.position = PlayerPosition.ROOM;
    LOG.info("[百家乐]玩家[{}]进入房间[{}]成功", player.userName, player.roomId);

    player.tables.clear();
    player.members.clear();

    ResEnterRoomMsg msg = (ResEnterRoomMsg) message;

    for (TableInfo table : msg.getTables()) {
      LOG.info("百家乐 桌子 id= {}", table.getId());
      player.tables.put(table.getId(), table);
    }

    for (MemInfo member : msg.getMembers()) {
      player.members.put(member.getPlayerId(), member);
    }

    // player.enterTable();

  }
}
