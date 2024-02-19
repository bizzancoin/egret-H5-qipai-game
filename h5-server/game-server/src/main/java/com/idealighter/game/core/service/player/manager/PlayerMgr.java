
package com.idealighter.game.core.service.player.manager;

import com.alibaba.fastjson.JSON;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.gamehall.event.RoomMemInfoUpdateEvent;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.recharge.RechargeLog;
import com.idealighter.game.core.service.log.struct.resource.CreditLog;
import com.idealighter.game.core.service.log.struct.resource.GoldLog;
import com.idealighter.game.core.service.log.struct.resource.IngotLog;
import com.idealighter.game.core.service.log.struct.resource.SafeGoldLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.service.IPlayerService;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.LevelExpDic;
import com.idealighter.game.dictionary.domain.LevelExpDomain;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.game.server.event.ShutdownEvent;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 玩家管理 .
 * 
 * @date 2015年7月31日 下午10:25:33
 *
 */
@Singleton
public class PlayerMgr implements IPlayerMgr {

  private static final Logger LOG = LoggerFactory.getLogger(PlayerMgr.class);
  private static final Logger SAVE_PLAYER_FAIL_LOG = LoggerFactory.getLogger("SavePlayerFailLog");
  // 7天
  public static final long ACTIVE_DAY = 7;

  @Inject
  private PlayerMsgMgr playerMsgMgr;
  @Inject
  private ExecutorMgr executorMgr;
  @Inject
  private LoginMgr loginMgr;

  @Inject
  private LevelExpDic levelExpDic;

  private final EventMgr eventMgr;

  private final IPlayerService playerService;

  // 活跃玩家(包含了在线的数据)
  private final ConcurrentHashMap<Long, PlayerBo> activePlayers = new ConcurrentHashMap<>();
  // 在线玩家
  private final ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<>();
  // 异步保存数据executor
  private final DisruptorExecutor playerSaveExecutor = new DisruptorExecutor("player-saveExecutor");

  /**
   * 构造函数 .
   * 
   * @param playerService playerService.
   * @param scheduleMgr 定时器.
   * @param eventMgr 事件.
   * @param resetOnline 充值在线flag.
   */
  @Inject
  public PlayerMgr(IPlayerService playerService, ScheduleMgr scheduleMgr, EventMgr eventMgr,
      @Named("resetOnlineFlg") boolean resetOnline) {
    this.playerService = playerService;
    this.eventMgr = eventMgr;
    eventMgr.register(this);
    if (resetOnline) {
      // 重置在线标示,以防服务器异常关服
      playerService.resetOnline();
    }

    playerSaveExecutor.startUp();
    // 每隔5分钟保存一次在线玩家
    scheduleMgr.scheduleWithFixedDelay(this::updateOnlinePlayers, 5, 5, TimeUnit.MINUTES, null);
    // 每隔半小时清理一次活跃玩家
    scheduleMgr.scheduleWithFixedDelay(this::refreshActivePlayers, 30, 30, TimeUnit.MINUTES, null);
    // 每一分钟给在线玩家加1经验值
    // scheduleMgr.scheduleWithFixedDelay(() -> {
    // for (Player player : players.values()) {
    // addExp(player, 20);
    // }
    // }, 0, 10, TimeUnit.SECONDS, null);

    // 加载活跃玩家
    loadActivePlayers();
  }

  /**
   * 加载活跃玩家,距离上一次登录或者登出时间为7天的玩家.
   */
  private void loadActivePlayers() {
    // 活跃玩家的时间点
    Date activeTime = activePlayerTime();
    List<PlayerBo> selectActivePlayers = playerService.selectActivePlayers(activeTime);

    for (PlayerBo playerDomain : selectActivePlayers) {
      activePlayers.put(playerDomain.getId(), playerDomain);
    }

    LOG.info("共加载了[{}]位活跃玩家数据", activePlayers.size());
  }

  /**
   * 刷新活跃玩家,清理登录时间和登出时间都小于活跃玩家时间点.
   */
  private void refreshActivePlayers() {
    // 活跃玩家的时间点
    Date activeTime = activePlayerTime();

    Iterator<PlayerBo> activePlayersIt = activePlayers.values().iterator();
    while (activePlayersIt.hasNext()) {
      PlayerBo playerDomain = activePlayersIt.next();
      Date loginTime = playerDomain.getLoginTime();
      Date logoutTime = playerDomain.getLogoutTime();

      // 7天没有登录和下线视为非活跃玩家
      if (loginTime != null && loginTime.getTime() < activeTime.getTime() && logoutTime != null
          && logoutTime.getTime() < activeTime.getTime()) {
        activePlayersIt.remove();
        LOG.info("玩家[{}][{}]上次登录和退出超过7天,从活跃玩家中清理出去", playerDomain.getId(),
            playerDomain.getPlayerName());
      }
    }
  }

  /**
   * 活跃玩家时间点.
   * 
   * @return
   */
  private Date activePlayerTime() {
    LocalDateTime dateTime = LocalDateTime.now().minusDays(ACTIVE_DAY);
    return Timestamp.valueOf(dateTime);
  }

  /**
   * 登记在线玩家 .
   * 
   * @param player 玩家.
   */
  @Override
  public void registerPlayer(Player player) {
    players.put(player.getId(), player);
    // 重新注册，发现已经有旧的信息则更新
    PlayerBo playerBo = activePlayers.get(player.getId());
    if (playerBo != null) {
      updatePlayer(playerBo);
      player.setPlayerBo(playerBo);
    } else {
      activePlayers.put(player.getId(), player.playerBo());
    }
  }

  /**
   * 取消登记在线玩家,活跃玩家不变，活跃玩家通过定时器来刷新 .
   * 
   * @param player 玩家.
   */
  @Override
  public void unregisterPlayer(Player player) {
    players.remove(player.getId());
  }

  /**
   * 获取在线玩家 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  @Override
  public Player getPlayer(long playerId) {

    return players.get(playerId);
  }

  /**
   * 在线玩家人数 .
   * 
   * @return
   */
  @Override
  public int onLinePlayersNum() {

    return players.size();
  }

  /**
   * 玩家是否在线 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  @Override
  public boolean onLine(long playerId) {

    return players.containsKey(playerId);
  }

  /**
   * 在线玩家 .
   * 
   * @return
   */
  @Override
  public ConcurrentHashMap<Long, Player> onLinePlayers() {

    return players;
  }

  /**
   * 异步更新玩家在线 .
   * 
   * @param playerId 玩家id.
   * @param online .
   */
  @Override
  public void asyncUpdateOnline(long playerId, boolean online) {
    playerSaveExecutor.execute(() -> playerService.updateOnlineByPlayerId(playerId, online));
  }

  /**
   * 同步保存在线玩家数据.
   */
  @Override
  public void updateOnlinePlayers() {
    players.values().forEach(p -> updatePlayer(p.playerBo()));

    for (Iterator<Entry<Long, Player>> iterator = players.entrySet().iterator(); iterator
        .hasNext();) {
      Entry<Long, Player> playerEntry = iterator.next();
      Player tempPlayer = playerEntry.getValue();

      if (!tempPlayer.isOnline()) {
        iterator.remove();
      }
    }
  }

  /**
   * 同步保存玩家数据 .
   * 
   * @param playerDom .
   */
  @Override
  public void updatePlayer(PlayerBo playerDom) {
    try {
      // 如果保存数据失败(如：数据库连接断开,网络异常等)则保存数据到日志文件以便恢复
      playerService.updateById(playerDom);
    } catch (Exception e) {
      if (!playerDom.isRobot()) {
        SAVE_PLAYER_FAIL_LOG.error(String.format("玩家[%s][%s]数据[%s]保存失败", playerDom.getPlayerName(),
            playerDom.getId(), JSON.toJSONString(playerDom)), e);
      }
    }
  }

  /**
   * 异步保存玩家 .
   * 
   * @param player 玩家.
   */
  @Override
  public void asyncUpdatePlayer(Player player) {
    playerSaveExecutor.execute(() -> this.updatePlayer(player.playerBo()));
  }

  @Override
  public void asyncUpdateSuperId(Player player) {
    playerSaveExecutor
        .execute(() -> playerService.updateSuperIdByPlayerId(player.getId(), player.getSuperId()));
  }

  /**
   * 同步插入玩家数据.
   */
  @Override
  public void insertPlayer(Player player) {
    try {
      // 如果保存数据失败(如：数据库连接断开,网络异常等)则保存数据到日志文件以便恢复
      playerService.insert(player.playerBo());
    } catch (Exception e) {
      if (!player.isRobot()) {
        SAVE_PLAYER_FAIL_LOG.error(String.format("玩家[%s][%s]数据[%s]插入失败", player.getPlayerName(),
            player.getId(), JSON.toJSONString(player.playerBo())), e);
      }
    }
  }

  /**
   * 增加vip时长 .
   * 
   * @param player 玩家.
   * @param duration . 天
   */
  @Override
  public void addVipDuration(Player player, int duration) {
    long vipEndTime = player.getVipEndTime();
    long now = System.currentTimeMillis();
    if (vipEndTime < now) {
      vipEndTime = now + duration * 24L * 60 * 60 * 1000;
      // vip有变化,触发房间大厅成员信息变更事件
      eventMgr.post(new RoomMemInfoUpdateEvent(player));
    } else {
      vipEndTime = vipEndTime + duration * 24L * 60 * 60 * 1000;
    }
    player.setVipEndTime(vipEndTime);
    playerMsgMgr.noticeAttrChangeMsg(player, 8, (vipEndTime - now) / 1000);
  }

  /**
   * 增加元宝 .
   * 
   * @param player 玩家.
   * @param ingot .
   * @param reason . 增加元宝reason
   */
  @Override
  public void addIngot(Player player, long ingot, LogReason reason) {
    if (ingot == 0) {
      LOG.warn("玩家[{}][{}]增加的元宝不能为0", player.getPlayerName(), player.getId());
      return;
    }

    long beforeIngot = player.getIngot();
    long afterIngot = player.playerBo().getIngot().addAndGet(ingot);

    playerMsgMgr.noticeAttrChangeMsg(player, 1, afterIngot);
    eventMgr.post(new RoomMemInfoUpdateEvent(player));
    DbLogService.log(new IngotLog(player, beforeIngot, afterIngot, reason));
  }


  /**
   * 直接给PlayerDomain增加元宝 .
   * 
   * @param playerDomain .
   * @param ingot .
   * @param reason .
   */
  @Override
  public void addIngot(PlayerBo playerDomain, long ingot, LogReason reason) {
    if (ingot == 0) {
      LOG.warn("玩家[{}][{}]增加的元宝不能为0", playerDomain.getPlayerName(), playerDomain.getId());
      return;
    }

    long beforeIngot = playerDomain.getIngot().get();
    long afterIngot = playerDomain.getIngot().addAndGet(ingot);

    DbLogService.log(new IngotLog(playerDomain, beforeIngot, afterIngot, reason));
  }

  /**
   * 增加经验,经验在游戏中用处不大，无需记录日志 .
   * 
   * @param player 玩家.
   * @param exp .
   */
  @Override
  public void addExp(Player player, long exp) {
    long lastExp = player.getExp() + exp;
    player.setExp(lastExp);
    // 客户端不用经验，暂时不同步
    // playerMsgMgr.sendAttrChangeMsg(player, 9, lastExp);

    /*
     * 计算新等级 . 所需经验 1:0级校验 + 1 * 1 . * 1 . = 1 2:1级校验 + 2 * 2 . * 2 . = (1 + 2) * (1 . + 2) 3:2级校验 +
     * 3 * 3 . * 3 . = (1 . + 2 + 3) * (1 . + 2 + 3)
     */
    int nextLevel = player.getLevel();
    int index = nextLevel;
    for (; index < levelExpDic.list().size() - 1; index++) {
      if (lastExp >= levelExpDic.list().get(index).getExp()
          && lastExp < levelExpDic.list().get(index + 1).getExp()) {
        nextLevel = levelExpDic.list().get(index).getLevel();
        break;
      }
    }
    if (index == levelExpDic.list().size() - 1 && nextLevel > player.getLevel()) {
      nextLevel = levelExpDic.list().get(index).getLevel();
    }

    if (player.getLevel() < nextLevel) {
      player.setLevel(nextLevel);
      eventMgr.post(new RoomMemInfoUpdateEvent(player));
      playerMsgMgr.noticeAttrChangeMsg(player, 6, nextLevel);

      LevelExpDomain expDomain = levelExpDic.map().get(player.getLevel() + 1);
      if (expDomain == null) {
        expDomain = levelExpDic.map().get(levelExpDic.getMaxLevel());
      }
      playerMsgMgr.noticeAttrChangeMsg(player, 11, expDomain.getExp());
    }
    playerMsgMgr.noticeAttrChangeMsg(player, 9, lastExp);
  }

  /**
   * 玩家不在线，添加经验.
   * 
   * @param playerDom 玩家.
   * @param exp 经验.
   */
  @Override
  public void addExp(PlayerBo playerDom, long exp) {

    if (exp == 0) {
      return;
    }
    long beforeExp = playerDom.getExp();
    long lastExp = beforeExp + exp;
    playerDom.setExp(lastExp);

    int nextLevel = playerDom.getLevel();
    int index = nextLevel;
    for (; index < levelExpDic.list().size() - 1; index++) {
      if (lastExp >= levelExpDic.list().get(index).getExp()
          && lastExp < levelExpDic.list().get(index + 1).getExp()) {
        nextLevel = levelExpDic.list().get(index).getLevel();
        break;
      }
    }
    if (index == levelExpDic.list().size() - 1) {
      nextLevel = levelExpDic.list().get(index).getLevel();
    }

    if (playerDom.getLevel() < nextLevel) {
      playerDom.setLevel(nextLevel);
    }
  }


  /**
   * 减少元宝 .
   * 
   * @param player 玩家.
   * @param ingot .
   * @param reason . 减少元宝reason
   */
  @Override
  public void minusIngot(Player player, int ingot, LogReason reason) {
    addIngot(player, -ingot, reason);
  }

  /**
   * 增加金币 .
   * 
   * @param player 玩家.
   * @param gold .
   * @param reason .
   */
  @Override
  public void addGold(Player player, long gold, LogReason reason) {
    addGold(player, gold, true, true, reason);
  }

  /**
   * 增加金币 .
   * 
   * @param player 玩家.
   * @param gold .
   * @param log . 是否记录log
   * @param notice . 是否房间通知
   * @param reason .
   */
  @Override
  public void addGold(Player player, long gold, boolean log, boolean notice, LogReason reason) {
    if (gold == 0) {
      // LOG.warn("玩家[{}][{}]增加的金币不能为0", player.getPlayerName(), player.getId());
      return;
    }

    long beforeGold = player.getGold();
    long afterGold = player.playerBo().getGold().addAndGet(gold);

    // 关闭金币变化通知
    // if (notice) {
    // eventMgr.post(new RoomMemInfoUpdateEvent(player));
    // playerMsgMgr.noticeAttrChangeMsg(player, 2, afterGold);
    // }

    if (log) {
      // 机器人不加log
      if (!player.isRobot()) {
        DbLogService.log(new GoldLog(player, beforeGold, afterGold, reason));
      }
    }
  }

  /**
   * 增加金币.
   * 
   * @param playerId 玩家id.
   * @param gold .
   * @param reason .
   */
  @Override
  public void addGold(long playerId, long gold, LogReason reason) {
    Player player = getPlayer(playerId);
    if (player != null) {
      addGold(player, gold, reason);
    } else {
      addGold(selectPlayer(playerId), gold, reason);
    }
  }

  /**
   * 直接给PlayerDomain增加金币 .
   * 
   * @param playerDom .
   * @param gold .
   * @param reason .
   */
  @Override
  public void addGold(PlayerBo playerDom, long gold, LogReason reason) {
    addGold(playerDom, gold, true, reason);
  }



  /**
   * 直接给PlayerDomain增加金币 .
   * 
   * @param playerDom .
   * @param gold .
   * @param log . 是否记录日志
   * @param reason .
   */
  @Override
  public void addGold(PlayerBo playerDom, long gold, boolean log, LogReason reason) {
    if (gold == 0) {
      // LOG.warn("玩家[{}][{}]增加的金币不能为0", playerDom.getPlayerName(), playerDom.getId());
      return;
    }

    long beforeGold = playerDom.getGold().get();
    long afterGold = playerDom.getGold().addAndGet(gold);

    if (log) {
      // 机器人不加log
      if (!playerDom.isRobot()) {
        DbLogService.log(new GoldLog(playerDom, beforeGold, afterGold, reason));
      }
    }
  }

  /**
   * 通知玩家金币更新 .
   * 
   * @param player 玩家.
   */
  @Override
  public void noticeGold(Player player) {
    if (player == null) {
      return;
    }
    long gold = player.getGold();
    eventMgr.post(new RoomMemInfoUpdateEvent(player));
    playerMsgMgr.noticeAttrChangeMsg(player, 2, gold);
  }

  /**
   * 增加赢分.
   * 
   * @param player 玩家.
   * @param gold 金币.
   */
  @Override
  public void addWinGold(Player player, long gold) {
    player.playerBo().getWinGold().addAndGet(gold);
  }

  /**
   * 增加赢分.
   * 
   * @param playerBo 玩家.
   * @param gold 金币.
   */
  @Override
  public void addWinGold(PlayerBo playerBo, long gold) {
    playerBo.getWinGold().get();
    playerBo.getWinGold().addAndGet(gold);
    // 更新用户信息
    updatePlayer(playerBo);
  }



  /**
   * 减少金币 .
   * 
   * @param player 玩家.
   * @param gold .
   * @param reason . 减少元宝reason
   */
  @Override
  public void minusGold(Player player, long gold, LogReason reason) {
    addGold(player, -gold, reason);
  }


  /**
   * 增加保险箱金币 .
   * 
   * @param player 玩家.
   * @param gold 金币 .
   * @param reason 原因.
   */
  @Override
  public void addSafeGold(Player player, long gold, String orderNo, LogReason reason) {
    if (gold == 0) {
      // LOG.warn("玩家[{}]增加的保险箱金币不能为0", playerId);
      return;
    }

    long beforeGold = player.getSafeGold();
    long afterGold = player.playerBo().getSafeGold().addAndGet(gold);

    playerMsgMgr.noticeAttrChangeMsg(player, 3, afterGold);

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new SafeGoldLog(player, beforeGold, afterGold, orderNo, reason));
    }
  }

  /**
   * 添加 保险箱金币(业务需要配合重试) .
   *
   * @author abin
   * @date 2018年6月23日 下午4:12:06
   * @param player 玩家.
   * @param beforeSafeGold 初始金币.
   * @param afterSafeGold 结束金币.
   * @param reason 原因.
   * @return 是否添加成功.
   */
  @Override
  public boolean addSafeGold(Player player, long beforeSafeGold, long afterSafeGold, String orderNo,
      LogReason reason) {
    boolean ok = false;
    synchronized (player) {
      ok = player.playerBo().getSafeGold().compareAndSet(beforeSafeGold, afterSafeGold);
      if (ok) {
        playerMsgMgr.noticeAttrChangeMsg(player, 3, afterSafeGold);

        // 机器人不加log
        if (!player.isRobot()) {
          DbLogService.log(new SafeGoldLog(player, beforeSafeGold, afterSafeGold, orderNo, reason));
        }
      }

    }
    return ok;
  }

  /**
   * 添加离线玩家保险箱金币.
   * 
   * @param playerBo 玩家.
   * @param gold 金币.
   * @param reason 原因.
   */
  @Override
  public void addSafeGold(PlayerBo playerBo, long gold, String orderNo, LogReason reason) {
    if (gold == 0) {
      // LOG.warn("玩家[{}]增加的保险箱金币不能为0", playerId);
      return;
    }

    long beforeGold = playerBo.getSafeGold().get();
    long afterGold = playerBo.getSafeGold().addAndGet(gold);

    // 机器人不加log
    if (!playerBo.isRobot()) {
      DbLogService.log(new SafeGoldLog(playerBo, beforeGold, afterGold, orderNo, reason));
    }
  }

  /**
   * 添加 保险箱金币(业务需要配合重试) .
   *
   * @author abin
   * @date 2018年6月23日 下午4:13:29
   * @param playerBo 离线玩家.
   * @param beforeSafeGold 添加之前金币.
   * @param afterSafeGold 添加之后金币.
   * @param reason 原因.
   * @return 添加是否成功.
   */
  @Override
  public boolean addSafeGold(PlayerBo playerBo, long beforeSafeGold, long afterSafeGold,
      String orderNo, LogReason reason) {
    boolean ok = playerBo.getSafeGold().compareAndSet(beforeSafeGold, afterSafeGold);
    if (ok) {
      // 机器人不加log
      if (!playerBo.isRobot()) {
        DbLogService.log(new SafeGoldLog(playerBo, beforeSafeGold, afterSafeGold, orderNo, reason));
      }
    }
    return ok;
  }

  // @Override
  // public void addAllGold(Player player, long safeGold, long gold, LogReason reason) {
  // if (gold == 0 && safeGold == 0) {
  // return;
  // }
  //
  // synchronized (player) {
  // long beforeGameGold = player.getLastLogGold();
  // long afterGameGold = beforeGameGold;
  // if (gold != 0) {
  // long afterGold = player.playerBo().getGold().addAndGet(gold);
  //
  // player.setLastLogGold(afterGold);
  //
  // afterGameGold = player.getLastLogGold();
  //
  // DbLogService.log(new GoldLog(player, beforeGameGold, afterGameGold, reason));
  // }
  //
  // long beforeSafeGold = player.getSafeGold();
  // long afterSafeGold = beforeSafeGold;
  // if (safeGold != 0) {
  // afterSafeGold = player.playerBo().getSafeGold().addAndGet(safeGold);
  // DbLogService.log(new SafeGoldLog(player, beforeSafeGold, afterSafeGold, reason));
  // playerMsgMgr.noticeAttrChangeMsg(player, 3, afterSafeGold);
  // }
  //
  //
  // DbLogService.log(new GameGoldLog(player, beforeSafeGold, afterSafeGold, reason,
  // beforeGameGold, afterGameGold));
  //
  // player.setLastLogGold(afterGameGold);
  // }
  // }

  // @Override
  // public void addAllGold(PlayerBo playerBo, long safeGold, long gold, LogReason reason) {
  // if (gold == 0 && safeGold == 0) {
  // return;
  // }
  //
  // synchronized (playerBo) {
  // long beforeGameGold = playerBo.getGold().get();
  // long afterGameGold = beforeGameGold;
  // if (gold != 0) {
  // afterGameGold = playerBo.getGold().addAndGet(gold);
  // DbLogService.log(new GoldLog(playerBo, beforeGameGold, afterGameGold, reason));
  // }
  //
  // long beforeSafeGold = playerBo.getSafeGold().get();
  // long afterSafeGold = beforeSafeGold;
  // if (safeGold != 0) {
  // afterSafeGold = playerBo.getSafeGold().addAndGet(safeGold);
  // DbLogService.log(new SafeGoldLog(playerBo, beforeSafeGold, afterSafeGold, reason));
  // }
  //
  // DbLogService.log(new GameGoldLog(playerBo, beforeSafeGold, afterSafeGold, reason,
  // beforeGameGold, afterGameGold));
  //
  // }
  // }

  /**
   * 更新玩家靓号.
   * 
   * @param player 玩家.
   * @param superId 靓号.
   */
  @Override
  public void changeUserTypeAndSuperId(Player player, long superId, byte playerType) {
    player.setSuperId(superId);
    player.setType(playerType);
    playerMsgMgr.noticeAttrChangeMsg(player, 12, superId);
    playerMsgMgr.noticeAttrChangeMsg(player, 13, playerType);
  }

  // /**
  // * 更新玩家superId.
  // *
  // * @param playerBo 玩家.
  // * @param superId 靓号.
  // */
  // public void changeUserTypeAndSuperId(PlayerBo playerBo, long superId, byte playerType) {
  // playerBo.setType(playerType);
  // playerBo.setSuperId(superId);
  // }



  /**
   * 增加积分 .
   * 
   * @param player 玩家.
   * @param credit .
   * @param reason .
   */
  @Override
  public void addCredit(Player player, long credit, LogReason reason) {
    if (credit == 0) {
      LOG.warn("玩家[{}][{}]增加的积分[{}]不能为0", player.getPlayerName(), player.getId());
      return;
    }

    long beforeCredit = player.getCedit();
    long afterCredit = player.playerBo().getCedit().addAndGet(credit);

    eventMgr.post(new RoomMemInfoUpdateEvent(player));
    playerMsgMgr.noticeAttrChangeMsg(player, 4, afterCredit);
    DbLogService.log(new CreditLog(player, beforeCredit, afterCredit, reason));
  }

  /**
   * 增加积分 .
   * 
   * @param playerDom .
   * @param credit .
   * @param reason .
   */
  @Override
  public void addCredit(PlayerBo playerDom, long credit, LogReason reason) {
    if (credit == 0) {
      LOG.warn("玩家[{}][{}]增加的积分[{}]不能为0", playerDom.getPlayerName(), playerDom.getId());
      return;
    }

    long beforeCredit = playerDom.getCedit().get();
    long afterCredit = playerDom.getCedit().addAndGet(credit);

    DbLogService.log(new CreditLog(playerDom, beforeCredit, afterCredit, reason));
  }

  /**
   * 减少积分 .
   * 
   * @param player 玩家.
   * @param credit .
   * @param reason .
   */
  @Override
  public void minusCredit(Player player, int credit, LogReason reason) {
    addCredit(player, -credit, reason);
  }

  /**
   * 给玩家充值,1人民币等于1元宝,在玩家Executor中执行保障线程安全 .
   * 
   * @param playerId 玩家id.
   * @param paywayname .
   * @param payway .
   * @param orderid .
   * @param payamount . 人民币
   * @param paygold . 赠送金币
   * @param providerorderid .
   * @return true:充值成功,false:玩家不存在
   */
  @Override
  public boolean recharge(long playerId, String orderid, String payway, String paywayname,
      long payamount, long paygold, String providerorderid) {
    final PlayerBo playerDom = selectPlayer(playerId);
    if (playerDom == null) {
      return false;
    }

    DisruptorExecutor executor = executorMgr.getPlayerExecutor(playerId);
    executor.execute(() -> {
      Player player = getPlayer(playerId);
      if (player != null) {
        // addIngot(player, payamount, LogReason.RECHARGE);
        addGold(player, paygold, LogReason.RECHARGE);
        addExp(player, payamount);
      } else {
        // 玩家不在线直接在更新PlayerDomain(缓存和数据库)
        // addIngot(playerDom, payamount, LogReason.RECHARGE);
        addGold(playerDom, paygold, LogReason.RECHARGE);
        updatePlayer(playerDom);
      }

      DbLogService.log(new RechargeLog(playerDom, orderid, payway, paywayname, payamount, paygold,
          providerorderid));
    });

    return true;
  }

  /**
   * 根据玩家id查找玩家,先在活跃玩家中找，找不到再去数据库找 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  @Override
  public PlayerBo selectPlayer(long playerId) {
    PlayerBo playerDomain = activePlayers.get(playerId);
    if (playerDomain == null) {
      playerDomain = playerService.selectById(playerId);
      if (playerDomain != null) {
        activePlayers.put(playerId, playerDomain);
      }
    }

    return playerDomain;
  }

  /**
   * 根据用户名查找玩家，先在活跃账号缓存找，找不到再去数据库找 .
   * 
   * @param userName .
   * @return
   */
  @Override
  public PlayerBo selectPlayer(String userName) {
    for (PlayerBo dom : activePlayers.values()) {
      if (dom.getUserName().equals(userName)) {
        return dom;
      }
    }

    PlayerBo playerDom = playerService.selectPlayerByUserName(userName);
    if (playerDom != null) {
      activePlayers.put(playerDom.getId(), playerDom);
    }

    return playerDom;
  }

  /**
   * 更新玩家游戏信息到数据库.
   * 
   * @Title updatePlayerGameInfo.
   * @author houdongsheng
   * @date 2018年1月29日 上午4:15:19
   * @param playerId 玩家编号
   */
  @Override
  public void updatePlayerGameInfo(Long playerId) {
    executorMgr.getPlayerExecutor(playerId).execute(() -> {
      PlayerBo domain = this.selectPlayer(playerId);
      if (domain != null) {
        this.playerService.updatePlayerGameInfoDomain(domain);
      }
    });
  }

  /**
   * 靓号取玩家.
   * 
   * @param superId 靓号.
   * @return 玩家.
   */
  @Override
  public PlayerBo selectPlayerBySuperId(long superId) {
    for (PlayerBo dom : activePlayers.values()) {
      if (dom.getSuperId() == superId) {
        return dom;
      }
    }

    PlayerBo playerDom = playerService.selectBySuperId(superId);
    if (playerDom != null) {
      activePlayers.put(playerDom.getId(), playerDom);
    }

    return playerDom;

  }



  /**
   * 根据手机号码查询玩家.
   * 
   * @Title selectPlayerByPhone.
   * @author houdongsheng
   * @date 2017年11月27日 上午11:37:16
   * @param phone 手机号码
   * @return 玩家信息
   */
  @Override
  public PlayerBo selectPlayerByPhone(String phone) {
    for (PlayerBo dom : activePlayers.values()) {
      if (phone.equals(dom.getPhone())) {
        return dom;
      }
    }

    PlayerBo playerDom = playerService.selectPlayerByPhone(phone);
    if (playerDom != null) {
      activePlayers.put(playerDom.getId(), playerDom);
    }

    return playerDom;
  }

  /**
   * 根据unionId查询玩家.
   * 
   * @Title selectPlayerByUnionId.
   * @author houdongsheng
   */
  @Override
  public PlayerBo selectPlayerByUnionId(String unionId) {

    for (PlayerBo dom : activePlayers.values()) {
      if (unionId.equals(dom.getUnionId())) {
        return dom;
      }
    }

    PlayerBo playerDom = playerService.selectPlayerByUnionId(unionId);

    if (playerDom != null) {
      activePlayers.put(playerDom.getId(), playerDom);
    }

    return playerDom;
  }

  /**
   * 活跃玩家 .
   * 
   * @return
   */
  @Override
  public Collection<PlayerBo> activePlayers() {

    return activePlayers.values();
  }

  /**
   * 关服事件处理 .
   * 
   * @param event .
   */
  @Override
  @Subscribe
  public void onShutDown(ShutdownEvent event) {
    for (Player player : players.values()) {
      loginMgr.noticeLogoutForApplicationClose(player);
      if (player.getChannel() != null) {
        player.getChannel().close();
      }
    }
    LOG.info("所有玩家踢下线成功");

    LOG.info("保存玩家数据中...");
    for (PlayerBo playerDom : activePlayers.values()) {
      updatePlayer(playerDom);
    }


    LOG.info("保存玩家数据成功");
  }
}
