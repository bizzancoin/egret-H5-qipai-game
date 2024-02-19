
package com.idealighter.game.core.service.games.bairenniuniu.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.games.bairenniuniu.message.ReqFastEnterMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;


@ReqMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.FAST_ENTER)
public class ReqFastEnterHandler implements ReqMessageHandler {

  @Inject
  private BaiRenNiuNiuMgr baiRenMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    ReqFastEnterMsg msg = (ReqFastEnterMsg) message;
    return baiRenMgr.fastEnter(player, msg.getRoomId());
  }
}
