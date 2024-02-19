
package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.bairenniuniu.BaiRenNiuNiuPlayer;
import com.idealighter.game.games.bairenniuniu.message.ResBetMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.BET)
public class ResBetHandler implements ResMessageHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ResBetHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    BaiRenNiuNiuPlayer bairenNiuNiuPlayer = (BaiRenNiuNiuPlayer) player;
    ResBetMsg msg = (ResBetMsg) message;
    long bet = msg.getChips();
    // 总下注金币
    long bankerTotalBets = msg.getBankerTotalBets();

    // 玩家总下注
    bairenNiuNiuPlayer.totalBet += bet;
    // 所有玩家总下注
    bairenNiuNiuPlayer.bankerTotalBets = bankerTotalBets;

    LOG.info("[百人牛牛]玩家[{}][{}]下注成功，位置[{}]筹码[{}]", player.userName, player.playerId, msg.getArea(),
        msg.getChips());
  }
}
