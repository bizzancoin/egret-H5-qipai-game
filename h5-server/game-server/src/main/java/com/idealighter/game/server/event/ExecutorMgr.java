package com.idealighter.game.server.event;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.inject.Singleton;

import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.core.executor.DisruptorExecutorGroup;

import io.netty.channel.Channel;

import java.nio.charset.Charset;

/**
 * 游戏Executor管理 .
 * 
 * @date 2015年9月1日 下午4:28:51
 *
 */
@Singleton
public class ExecutorMgr {

  // 用户任务执行线程组,该线程组处理用户的任务，还未产生玩家的任务，如：用户登录，注册等用户任务。
  private final DisruptorExecutorGroup loginExecutorGroup =
      new DisruptorExecutorGroup(4, "login-executor");
  // 玩家任务执行线程组,该线程组处理玩家的任务，玩家之间不会共享数据的任务。
  private final DisruptorExecutorGroup playerExecutorGroup =
      new DisruptorExecutorGroup(4, "player-executor");
  // 具体棋牌游戏任务的执行线程组,如：逗地主，玩家之间需要共享棋牌游戏数据，封闭在具体线程内。
  private final DisruptorExecutorGroup gameExecutorGroup =
      new DisruptorExecutorGroup(12, "game-executor");
  private final DisruptorExecutorGroup authExecutorGroup =
      new DisruptorExecutorGroup(4, "auth-executor");

  private final HashFunction murmurHash = Hashing.murmur3_32();

  /**
   * executor构造.
   */
  public ExecutorMgr() {
    super();
    loginExecutorGroup.startUp();
    playerExecutorGroup.startUp();
    gameExecutorGroup.startUp();
    authExecutorGroup.startUp();
  }

  /**
   * 获取游戏模块id的Executor.
   * 
   * @param gameModuleId . 游戏模块Id.
   * @return executor.
   */
  public DisruptorExecutor getGameExecutor(int gameModuleId) {

    return gameExecutorGroup.getExecutor(gameModuleId % gameExecutorGroup.size());
  }

  /**
   * 获取玩家的Executor.
   * 
   * @param playerId 玩家id. 玩家Id.
   * @return executor.
   */
  public DisruptorExecutor getPlayerExecutor(long playerId) {

    return playerExecutorGroup.getExecutor((int) (playerId % playerExecutorGroup.size()));
  }

  /**
   * 获取登录的Executor(登录或者注册前的Executor).
   * 
   * @param channel . 频道.
   * @return executor.
   */
  public DisruptorExecutor getLoginExecutor(Channel channel) {

    return loginExecutorGroup.getExecutor(
        Math.abs(murmurHash.hashLong(channel.hashCode()).asInt()) % loginExecutorGroup.size());
  }

  /**
   * 获取登录的Executor(登录或者注册前的Executor).
   * 
   * @param channelHasCode . 频道的HashCode.
   * @return executor.
   */
  public DisruptorExecutor getLoginExecutor(int channelHasCode) {

    return loginExecutorGroup.getExecutor(
        Math.abs(murmurHash.hashLong(channelHasCode).asInt()) % loginExecutorGroup.size());
  }

  /**
   * 获取授权执行器 .
   *
   * @param sessionId .
   * @return 执行器.
   */
  public DisruptorExecutor getAuthExecutor(String sessionId) {

    return authExecutorGroup
        .getExecutor(Math.abs(murmurHash.hashString(sessionId, Charset.forName("UTF-8")).asInt())
            % loginExecutorGroup.size());
  }
}
