
package com.idealighter.game.core.service.games.bairenniuniu.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ReqMsgHandler;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.ReqMessageHandler;

/**
 * .
 *
 */
@ReqMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.CANCEL_APPLY_BANKER)
public class ReqCancelApplyBankerHandler implements ReqMessageHandler {

  @Inject
  private BaiRenNiuNiuMgr bairenMgr;

  @Override
  public ResMessage action(Player player, ReqMessage message) {
    // ReqCancelApplyBankerMsg msg = (ReqCancelApplyBankerMsg)message;
    return bairenMgr.cancelApplyBanker(player);
  }
}
