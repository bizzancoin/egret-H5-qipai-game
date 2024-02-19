
package com.idealighter.game.core.service.onlinereward.manager;

import com.google.inject.Inject;

import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.OnlineRewardDic;
import com.idealighter.game.dictionary.domain.OnlineRewardDomain;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.third.utils.ThirdOrderUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 在线挂机奖励管理 .
 * 
 * @date 2016年1月14日 下午8:36:35
 *
 */
// @Singleton
public class OnlineRewardMgr {

  // private final BankMgr bankMgr;
  private final PlayerMgr playerMgr;
  private final OnlineRewardDic onlineRewardDic;
  private final CommonMsgMgr commonMsgMgr;

  /**
   * 构造函数 .
   * 
   * @param playerMgr .
   * @param onlineRewardDic .
   * @param scheduleMgr .
   */
  @Inject
  public OnlineRewardMgr(PlayerMgr playerMgr, OnlineRewardDic onlineRewardDic,
      ScheduleMgr scheduleMgr, CommonMsgMgr commonMsgMgr) {

    this.playerMgr = playerMgr;
    this.onlineRewardDic = onlineRewardDic;
    this.commonMsgMgr = commonMsgMgr;
    scheduleMgr.scheduleAtFixedRate(this::rewardPlayersGold, 0, 1, TimeUnit.MINUTES, null);
  }

  /**
   * 奖励在线玩家金币 .
   */
  private void rewardPlayersGold() {
    Collection<Player> players = playerMgr.onLinePlayers().values();
    for (Player player : players) {
      rewardPlayerGold(player);
    }
  }

  /**
   * 奖励玩家金币 .
   * 
   * @param player 玩家.
   */
  private void rewardPlayerGold(Player player) {
    AbstractRoom room = player.curRoom;
    long enterRoomTime = player.enterRoomTime;

    if (room == null || enterRoomTime == 0) {
      return;
    }

    int roomId = room.getId();
    // 在线奖励配置
    OnlineRewardDomain onlineRewardDom = onlineRewardDic.get(room.game().getType(), roomId);
    if (onlineRewardDom == null) {
      return;
    }

    if (!((onlineRewardDom.getVip() && player.vip())
        || (onlineRewardDom.getTenLevelUpper() && player.getLevel() > 10)
        || (onlineRewardDom.getTenLevelLower() && player.getLevel() <= 10))) {
      return;
    }


    long now = System.currentTimeMillis();

    // 进入房间时长(分钟)
    long roomDuration = TimeUnit.MILLISECONDS.toMinutes(now - enterRoomTime);
    // 可以获得奖励的房间时长
    int rewardRoomDuration = onlineRewardDom.getRoomDuration();

    if (onlineRewardDom.getRoomEffective() && rewardRoomDuration > 0 && roomDuration > 0
        && roomDuration % rewardRoomDuration == 0) { // 房间在线有效,且刚刚满足时间点
      playerMgr.addSafeGold(player, onlineRewardDom.getRoomGold(),
          ThirdOrderUtils.generateThirdOrderNo(), LogReason.ROOM_ONLINE_REWARD_GOLD);
      commonMsgMgr.sendShowDlgMsg(player, ErrorCode.TASK_ONLINE_REWARD.getCode(), Operator.SYSTEM,
          "" + onlineRewardDom.getRoomGold());
    }

    AbstractTable table = player.curTable;
    long enterTableTime = player.enterTableTime;
    // 可以获得奖励的桌子时长
    int rewardTableDuration = onlineRewardDom.getTableDuration();

    if (onlineRewardDom.getTableEffective() && rewardTableDuration > 0 && table != null
        && enterTableTime > 0) { // 桌子在线有效

      // 进入桌子时长(分钟)
      long tableDuration = TimeUnit.MILLISECONDS.toMinutes(now - enterTableTime);
      if (tableDuration > 0 && tableDuration % rewardTableDuration == 0) { // 刚刚满足时间点
        playerMgr.addSafeGold(player, onlineRewardDom.getTableGold(),
            ThirdOrderUtils.generateThirdOrderNo(), LogReason.TABLE_ONLINE_REWARD_GOLD);
        commonMsgMgr.sendShowDlgMsg(player, ErrorCode.TASK_GAME_REWARD.getCode(), Operator.SYSTEM,
            "" + onlineRewardDom.getTableGold());
      }
    }
  }

}
