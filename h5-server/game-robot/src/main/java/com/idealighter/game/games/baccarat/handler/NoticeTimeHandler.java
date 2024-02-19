
package com.idealighter.game.games.baccarat.handler;

import com.google.inject.Inject;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.dictionary.dic.BaccaratTimeDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.baccarat.BaccaratPlayer;
import com.idealighter.game.games.baccarat.message.NoticeTimeMsg;
import com.idealighter.game.games.baccarat.message.ReqExitTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Baccarat.NOTICE_TIME)
public class NoticeTimeHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(NoticeTimeHandler.class);

  private static final int BET = 1;
  private static final int BALANCE = 3;

  @Inject
  BaccaratTimeDic timeDic;

  @Override
  public void action(Player player, ResMessage message) {
    NoticeTimeMsg msg = (NoticeTimeMsg) message;
    BaccaratPlayer baccaratPlayer = (BaccaratPlayer) player;
    MemInfo member = player.member(player.playerId);
    baccaratPlayer.state = msg.getState();

    if (msg.getState() == BET) {

      if (member.getChips() == 0) {
        if (player.playerInfo.getSafeGold() <= 0) {
          player.delayLogout();
          return;
        }
        baccaratPlayer.exchangeChips(player);
      }

      int betTime = timeDic.get(BET).getTime();
      if (betTime > msg.getTime()) {
        betTime = msg.getTime();
      }
      if (betTime > 0) {
        for (int i = 0; i < RandCodeUtil.random(5, 10) && betTime > 0; i++) {
          int randomTime = RandCodeUtil.random(1, betTime);
          player.schedule(() -> {
            baccaratPlayer.dobet();
          }, randomTime, TimeUnit.SECONDS);
        }
      }


    } else if (msg.getState() == BALANCE) {
      RobotConfigDomain robotCfg = player.configDomain;
      BaccaratRoomDomain roomDomain = ((BaccaratPlayer) player).roomDomain;
      baccaratPlayer.totalBet = 0;
      // 超过最大携带金币需要退出房间
      if (player.playerInfo.getSafeGold() + convertGold(roomDomain, member.getChips()) > robotCfg
          .getMaxGold()) {
        LOG.info("[百家乐]玩家[{}][{}]中身上的金币超过最大携带金币", player.userName, player.playerId,
            robotCfg.getMaxGold());
        player.delayLogout();
        return;
      }
      if (player.playerInfo.getSafeGold() + convertGold(roomDomain, member.getChips()) < roomDomain
          .getLower()) {
        LOG.info("[百家乐]玩家[{}][{}]中身上的金币不满足房间的最小金币数[{}]", player.userName, player.playerId,
            roomDomain.getLower());
        player.delayLogout();
        return;
      }

      // 超过最大游戏局数
      if (player.totalGameNum-- < 1) {
        LOG.info("[百家乐]玩家[{}][{}]中超过总游戏局数退出游戏...", player.userName, player.playerId);
        player.delayLogout();
        return;
      }
      LOG.info("[百家乐]玩家[{}][{}]结算", player.userName, player.playerId);
      // 超过换桌局数
      if (player.tableGameNum-- < 1) {
        player.tableGameNum =
            RandCodeUtil.random(robotCfg.getTableGameLower(), robotCfg.getTableGameUpper());
        LOG.info("[百家乐]玩家[{}][{}]中超过换桌局数换桌...", player.userName, player.playerId);
        player.sendMsg(new ReqExitTableMsg());
      }
    }
  }

  /**
   * 筹码换算金币.
   * 
   * @param roomId .
   * @param chips .
   */
  public long convertGold(BaccaratRoomDomain roomDomain, long chips) {
    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();
  }
}
