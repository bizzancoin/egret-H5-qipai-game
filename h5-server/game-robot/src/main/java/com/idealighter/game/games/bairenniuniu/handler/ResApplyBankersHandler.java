
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.bairenniuniu.message.ResApplyBankersMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_APPLY_BANKER)
public class ResApplyBankersHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResApplyBankersHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResApplyBankersMsg msg = (ResApplyBankersMsg) message;
    // 替换申请坐庄的玩家列表
    player.applyBankers.clear();
    player.applyBankers.addAll(msg.getApplicants());
  }
}
