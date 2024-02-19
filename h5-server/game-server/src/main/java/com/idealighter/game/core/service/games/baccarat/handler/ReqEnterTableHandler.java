
package com.idealighter.game.core.service.games.baccarat.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.baccarat.manager.BaccaratMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.baccarat.message.ReqEnterTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.Baccarat.ENTER_TABLE)
public class ReqEnterTableHandler implements ReqMessageHandler {
  @Inject
  private BaccaratMgr baccMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqEnterTableMsg msg = (ReqEnterTableMsg) message;

    return baccMgr.enterTable(player, msg.getTableId());
  }
}
