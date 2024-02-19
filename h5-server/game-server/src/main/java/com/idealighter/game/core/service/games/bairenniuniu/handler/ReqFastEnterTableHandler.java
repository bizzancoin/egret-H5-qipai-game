
package com.idealighter.game.core.service.games.bairenniuniu.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.bairenniuniu.message.ReqFastEnterTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.FAST_ENTER_TABLE)
public class ReqFastEnterTableHandler implements ReqMessageHandler {

  @Inject
  private BaiRenNiuNiuMgr baiRenMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqFastEnterTableMsg msg = (ReqFastEnterTableMsg) message;
    return baiRenMgr.fastEnterTable(player, msg.getRoomId());
  }
}
