
package com.idealighter.game.games.bairenniuniu.handler;

import com.google.inject.Inject;
import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.dictionary.dic.BairenniuniuTimeDic;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.bairenniuniu.BaiRenNiuNiuPlayer;
import com.idealighter.game.games.bairenniuniu.message.ReqApplyBankerMsg;
import com.idealighter.game.games.bairenniuniu.message.ReqExitTableMsg;
import com.idealighter.game.games.bairenniuniu.message.ResTimeMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import com.idealighter.utils.code.RandCodeUtil;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.RES_TIME)
public class ResTimeHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResTimeHandler.class);

  private static final int REWARD = 3;
  private static final int BET = 2;
  private static final int REST = 1;

  @Inject
  BairenniuniuTimeDic timeDic;

  @Override
  public void action(Player player, ResMessage message) {
    ResTimeMsg msg = (ResTimeMsg) message;
    BaiRenNiuNiuPlayer brnnPlayer = (BaiRenNiuNiuPlayer) player;
    BairenniuniuRoomDomain roomDom = brnnPlayer.roomDomain;
    MemInfo member = player.member(player.playerId);
    brnnPlayer.state = msg.getState();
    if (msg.getState() == BET) {
      if (member.getChips() == 0) {
        if (player.playerInfo.getSafeGold() <= 0) {
          player.delayLogout();
          return;
        }
        brnnPlayer.exchangeChips(player);
      }

      // 非庄家才能下注
      if (player.bankerId != player.playerId) {
        int betTime = timeDic.get(BET).getTime();
        for (int i = 0; i < RandCodeUtil.random(5, 10); i++) {
          player.schedule(() -> brnnPlayer.dobet(), RandCodeUtil.random(betTime),
              TimeUnit.SECONDS);
        }
      }
    } else if (msg.getState() == REWARD) {
      // 结算阶段
      LOG.info("[百人牛牛]玩家[{}][{}]结算阶段", player.userName, player.playerId);
    } else if (msg.getState() == REST) {
      RobotConfigDomain robotCfg = player.configDomain;
      brnnPlayer.totalBet = 0;
      brnnPlayer.bankerTotalBets = 0L;
      LOG.info("[百人牛牛]玩家[{}][{}]休息阶段", player.userName, player.playerId);

      BairenniuniuRoomDomain roomDomain = ((BaiRenNiuNiuPlayer) player).roomDomain;

      // 超过最大携带金币需要退出房间
      if (player.playerInfo.getSafeGold() + convertGold(roomDomain, member.getChips()) > robotCfg
          .getMaxGold()) {
        LOG.info("[百人牛牛]玩家[{}][{}]中身上的金币超过最大携带金币", player.userName, player.playerId,
            robotCfg.getMaxGold());
        player.delayLogout();
        return;
      }

      if (player.playerInfo.getSafeGold()
          + convertGold(roomDomain, member.getChips()) < roomDomain.getLower()) {
        LOG.info("[百人牛牛]玩家[{}][{}]中身上的金币不满足房间的最小金币数[{}]", player.userName, player.playerId,
            roomDomain.getLower());
        player.delayLogout();
        return;
      }

      // 超过最大游戏局数
      if (player.totalGameNum-- < 1) {
        LOG.info("[百人牛牛]玩家[{}][{}]中超过总游戏局数退出游戏...", player.userName, player.playerId);
        player.delayLogout();
        return;
      }

      // 超过换桌局数
      if (player.tableGameNum-- < 1) {
        player.tableGameNum =
            RandCodeUtil.random(robotCfg.getTableGameLower(), robotCfg.getTableGameUpper());
        LOG.info("[百人牛牛]玩家[{}][{}]中超过换桌局数换桌...", player.userName, player.playerId);
        // 退出桌子后，重新进入房间(随机)
        player.sendMsg(new ReqExitTableMsg());
        return;
      }
      // 申请坐庄
      if (!player.applyBankers.contains(player.playerId) // 尚未申请上庄
          && player.bankerId != player.playerId // 也不是庄家
          && player.member(player.playerId).getChips() >= roomDom.getBeBankerChips()) {
        int restTime = timeDic.get(REST).getTime();
        // 申请上庄
        boolean applyBanker = RandCodeUtil.randomBoolean();
        if (applyBanker) {
          player.schedule(() -> {
            player.sendMsg(new ReqApplyBankerMsg());
          }, RandCodeUtil.random(restTime), TimeUnit.SECONDS);
        }
      }
    }
  }

  /**
   * 筹码换算金币.
   * 
   * @param roomId .
   * @param chips .
   */
  public long convertGold(BairenniuniuRoomDomain roomDomain, long chips) {
    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();
  }
}
