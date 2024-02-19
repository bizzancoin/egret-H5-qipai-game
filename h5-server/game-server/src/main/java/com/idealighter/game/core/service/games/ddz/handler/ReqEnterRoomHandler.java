
package com.idealighter.game.core.service.games.ddz.handler;

import com.google.inject.Inject;
import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.ddz.manager.DdzMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.ddz.message.ReqEnterRoomMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.DDZ.ENTER_ROOM)
public class ReqEnterRoomHandler implements ReqMessageHandler {

  @Inject
  private DdzMgr ddzMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqEnterRoomMsg msg = (ReqEnterRoomMsg) message;
    return ddzMgr.enterRoom(player, msg.getRoomId());
  }
}
