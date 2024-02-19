
package com.idealighter.game.core.service.prizepool.manager;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.prizepool.struct.control.GameControl;
import com.idealighter.game.core.service.prizepool.struct.control.GameControlMap;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;

/**
 * 玩家个人奖池管理 .
 * 
 * @date 2015年10月13日 下午2:33:47
 *
 */
@Singleton
public class PlayerPrizePoolMgrScript {
  @Inject
  private PlayerMgr playerMgr;

  /**
   * 获取游戏的玩家个人奖池多个，第一个有效 .
   * 
   * @param playerId 玩家id.
   * @param game .
   * @return
   */
  public PlayerPrizePool prizePool(long playerId, Game game) {
    PlayerBo playerDomain = playerMgr.selectPlayer(playerId);
    return playerDomain.prizePools().get(game.getType());
  }

  /**
   * 增加玩家个人游戏奖池额 .
   * 
   * @param playerId 玩家id.
   * @param gameId .
   * @param gold .
   */
  public void addPrizePoolGold(long playerId, int gameId, long gold) {
    PlayerPrizePool prizePool = prizePool(playerId, Game.getGame(gameId));

    prizePool.getPrize().getAndAdd(gold);

    playerMgr.updatePlayerGameInfo(playerId);
  }

  /**
   * 更新个人游戏奖池金额 .
   * 
   * @param playerId 玩家id.
   * @param gameId .
   * @param gold .
   */
  public void updatePrizePoolGold(long playerId, int gameId, long gold) {
    PlayerPrizePool prizePool = prizePool(playerId, Game.getGame(gameId));
    prizePool.getPrize().getAndSet(gold);

    playerMgr.updatePlayerGameInfo(playerId);
  }

  /**
   * 更新个人游戏奖池控制 .
   * 
   * @param playerId 玩家id.
   * @param gameId .
   * @param control .
   */
  public void updatePrizePoolControl(long playerId, int gameId, String control) {
    Class<? extends GameControl> controlClz = GameControlMap.getGameControlGameId(gameId);
    GameControl gameControl = JSON.parseObject(control, controlClz);
    PrizePoolCheck.checkControl(gameControl);

    PlayerPrizePool prizePool = prizePool(playerId, Game.getGame(gameId));
    prizePool.setControl(control);

    playerMgr.updatePlayerGameInfo(playerId);
  }

  /**
   * 新增个人游戏奖池 .
   * 
   * @param playerId 玩家id.
   * @param gameId .
   * @param gold .
   * @param control .
   */
  public void addPrizePool(long playerId, int gameId, long gold, String control) {
    Class<? extends GameControl> controlClz = GameControlMap.getGameControlGameId(gameId);
    GameControl gameControl = JSON.parseObject(control, controlClz);
    PrizePoolCheck.checkControl(gameControl);

    PlayerPrizePool prizePool = new PlayerPrizePool(gameId, gold, control);
    PlayerBo playerDomain = playerMgr.selectPlayer(playerId);
    playerDomain.prizePools().put(gameId, prizePool);

    playerMgr.updatePlayerGameInfo(playerId);
  }
}
