
package com.idealighter.game.core.service.prizepool.manager;

import com.alibaba.fastjson.JSON;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.dao.dao.RoomPrizePoolGroupDao;
import com.idealighter.game.core.dao.generate.domain.RoomPrizePoolGoupDomain;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;
import com.idealighter.game.core.service.prizepool.struct.room.GoldRangeRoomPrizePool;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePool;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePoolGroup;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePoolGroupKey;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.game.server.event.ShutdownEvent;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 房间金币奖池管理 .
 * 
 * @date 2015年10月13日 下午2:33:47
 */
@Singleton
public class RoomPrizePoolMgrScript {
  private static final AtomicLong strategySeed = new AtomicLong(System.currentTimeMillis());
  private static final AtomicLong goldPricePoolSeed = new AtomicLong(System.currentTimeMillis());
  private static final Logger LOG = LoggerFactory.getLogger(RoomPrizePoolMgrScript.class);
  private RoomPrizePoolGroupDao roomPrizePoolGroupDao;
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private ExecutorMgr executorMgr;

  // 金币奖池
  private final Map<RoomPrizePoolGroupKey, RoomPrizePoolGroup> poolGroups =
      new ConcurrentHashMap<>();

  /**
   * 构造函数.
   * 
   * @param roomPrizePoolGroupDao 奖池mapper.
   * @param scheduleMgr 定时器.
   * @param eventMgr 事件管理.
   */
  @Inject
  public RoomPrizePoolMgrScript(RoomPrizePoolGroupDao roomPrizePoolGroupDao,
      ScheduleMgr scheduleMgr, EventMgr eventMgr) {
    this.roomPrizePoolGroupDao = roomPrizePoolGroupDao;
    eventMgr.register(this);
    loadData();
    // 每一分钟提取一次,同时保存一次数据
    scheduleMgr.scheduleWithFixedDelay(this::withdrawalGold, 1, 1, TimeUnit.MINUTES, null);
  }

  /**
   * 加载房间金币奖池数据.
   */
  private void loadData() {
    for (RoomPrizePoolGoupDomain domain : this.roomPrizePoolGroupDao.selectAll()) {
      RoomPrizePoolGroupKey key = new RoomPrizePoolGroupKey(domain.getGame(), domain.getRoomId());
      RoomPrizePoolGroup group = new RoomPrizePoolGroup(domain);
      poolGroups.put(key, group);
    }
  }

  /**
   * 保存所有房间奖池组信息.
   */
  public void saveAllRoomPoolGroups() {
    for (RoomPrizePoolGroup group : poolGroups.values()) {
      roomPrizePoolGroupDao.updatePrizePoolGroup(group.domain());
    }
  }

  /**
   * 提取游戏奖池中的金币.
   */
  private void withdrawalGold() {
    for (RoomPrizePoolGroup prizePoolGroup : poolGroups.values()) {
      Game game = Game.getGame(prizePoolGroup.getGame());
      // 在游戏线程中执行避免定时线程和游戏线程因为共享奖池出现脏数据
      executorMgr.getGameExecutor(game.getModuleId()).execute(() -> withdrawalGold(prizePoolGroup));
    }
  }

  /**
   * 提取奖池组中的金币 .
   * 
   * @param prizePoolGroup .
   */
  private void withdrawalGold(RoomPrizePoolGroup prizePoolGroup) {
    try {
      RoomPrizePool defaultPrizePool = prizePoolGroup.getDefaultPrizePool();
      if (defaultPrizePool != null) {
        // 默认奖池提取
        withdrawalGold(defaultPrizePool);
      }

      List<GoldRangeRoomPrizePool> goldRangePrizePools = prizePoolGroup.getGoldRangePrizePools();
      if (goldRangePrizePools != null && goldRangePrizePools.size() > 0) {
        for (GoldRangeRoomPrizePool prizePool : goldRangePrizePools) {
          // 金币范围奖池提取
          withdrawalGold(prizePool);
        }
      }

      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    } catch (Exception e) {
      LOG.error("提取游戏[" + prizePoolGroup.getGame() + "]房间[" + prizePoolGroup.getRoomId() + "]奖池失败",
          e);
    }
  }

  /**
   * 提取奖池金币 .
   * 
   * @param prizePool .
   */
  private void withdrawalGold(RoomPrizePool prizePool) {
    AtomicLong prizeGold = prizePool.getPrize();
    long now = System.currentTimeMillis();
    List<ControlStrategy> strategys = prizePool.getStrategys();
    if (strategys != null) {
      for (ControlStrategy stgy : strategys) {
        if (prizeGold.get() >= stgy.getLower() && prizeGold.get() <= stgy.getUpper()
            && TimeUnit.MILLISECONDS.toMinutes(now) % stgy.getTime() == 0) {
          if (stgy.getWithdrawType() == 0) { // 按金币提取
            prizeGold.getAndAdd(-stgy.getGold());
          } else { // 按比例提取
            long withdrawGold = prizeGold.get() * stgy.getRatio() / 100;
            prizeGold.getAndAdd(-withdrawGold);
          }
        }
      }
    }
  }

  /**
   * 获取玩家的房间奖池,房间有很多奖池但是只有一个会生效,默认奖池的优先级最低 .
   * 
   * @param playerId 玩家id.
   * @param game .
   * @param roomId .
   * @return
   */
  public RoomPrizePool roomPrizePool(long playerId, Game game, int roomId) {
    RoomPrizePoolGroup poolGroup =
        poolGroups.get(new RoomPrizePoolGroupKey(game.getType(), roomId));
    if (poolGroup != null) {
      List<GoldRangeRoomPrizePool> prizePools = poolGroup.getGoldRangePrizePools();
      if (prizePools != null && prizePools.size() > 0) {
        PlayerBo playerDomain = playerMgr.selectPlayer(playerId);
        // 身上的钱和筹码兑换的钱
        long gold = playerDomain.getGold().get();

        for (GoldRangeRoomPrizePool prizePool : prizePools) {
          if (gold >= prizePool.getLower() && gold <= prizePool.getUpper()) {
            return prizePool;
          }
        }
      }

      return poolGroup.getDefaultPrizePool();
    }

    return null;
  }

  /**
   * 默认房间奖池 .
   * 
   * @param game .
   * @param roomId .
   * @return
   */
  public RoomPrizePool defaultRoomPrizePool(Game game, int roomId) {
    RoomPrizePoolGroup poolGroup = getRoomPrizePoolGroup(game, roomId);
    if (poolGroup != null) {
      return poolGroup.getDefaultPrizePool();
    }

    return null;
  }

  /**
   * 增加默认奖池金币,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param gold .
   */
  public void addDefaultPrizePoolGold(Game game, int roomId, long gold) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      RoomPrizePool defaultPrizePool = prizePoolGroup.getDefaultPrizePool();
      defaultPrizePool.getPrize().getAndAdd(gold);

      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 修改默认奖池金币,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param gold .
   */
  public void modifyDefaultPrizePoolGold(Game game, int roomId, long gold) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      RoomPrizePool defaultPrizePool = prizePoolGroup.getDefaultPrizePool();
      defaultPrizePool.getPrize().getAndSet(gold);
      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 增加金币范围奖池金币,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param prizePoolId 奖金池编号.
   * @param gold .
   */
  public void addGoldRangePrizePoolGold(Game game, int roomId, long prizePoolId, long gold) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == prizePoolId) {
            prizePool = item;
            break;
          }
        }
      }
      if (prizePool != null) {
        prizePool.getPrize().getAndAdd(gold);
        roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
      }
    });
  }

  /**
   * 修改金币范围奖池金币,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param prizePoolId 奖池编号.
   * @param gold .
   */
  public void modifyGoldRangePrizePoolGold(Game game, int roomId, long prizePoolId, long gold) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == prizePoolId) {
            prizePool = item;
            break;
          }
        }
      }
      if (prizePool != null) {
        prizePool.getPrize().getAndSet(gold);
        roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
      }
    });
  }

  /**
   * 修改金币范围奖池范围,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game 游戏编号.
   * @param roomId 房间编号.
   * @param poolId 奖池编号.
   * @param lower 玩家金币下限.
   * @param upper 玩家金币上限.
   */
  public void modifyPrizePoolRange(Game game, int roomId, long poolId, long lower, long upper) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == poolId) {
            prizePool = item;
            break;
          }
        }
      }
      if (prizePool != null) {
        prizePool.setLower(lower);
        prizePool.setUpper(upper);
        roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
      }
    });
  }

  /**
   * 删除默认奖池,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   */
  public void delDefaultRoomPrizePool(Game game, int roomId) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      prizePoolGroup.setDefaultPrizePool(null);
      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 删除默认金币范围奖池,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param poolId .
   */
  public void delGoldRangeRoomPrizePool(Game game, int roomId, long poolId) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == poolId) {
            prizePools.remove(item);
            roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
            break;
          }
        }
      }
    });
  }

  /**
   * 删除默认奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game 游戏编号.
   * @param roomId 房间编号.
   * @param strategyId 策略编号.
   */
  public void delDefaultRoomPrizePoolStrategy(Game game, int roomId, long strategyId) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);

      List<ControlStrategy> strategys = prizePoolGroup.getDefaultPrizePool().getStrategys();
      if (strategys != null) {
        for (int i = 0; i < strategys.size(); i++) {
          ControlStrategy item = strategys.get(i);
          if (item.getId() == strategyId) {
            strategys.remove(item);
            roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
            break;
          }
        }
      }
    });
  }

  /**
   * 删除金币范围奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param poolId 奖池编号.
   * @param strategyId 策略编号.
   */
  public void delGoldRangeRoomPrizePoolStrategy(Game game, int roomId, long poolId,
      long strategyId) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == poolId) {
            prizePool = item;
            break;
          }
        }
      }
      if (prizePool != null) {
        List<ControlStrategy> strategys = prizePool.getStrategys();
        if (EmptyUtil.listIsNotEmpty(strategys)) {
          for (int i = 0; i < strategys.size(); i++) {
            ControlStrategy item = strategys.get(i);
            if (item.getId() == strategyId) {
              strategys.remove(item);
              roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
              break;
            }
          }
        }
      }
    });
  }

  /**
   * 修改默认奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param strategyId 策略编号.
   * @param strategy .
   */
  public void modifyDefaultRoomPrizePoolStrategy(Game game, int roomId, long strategyId,
      String strategy) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<ControlStrategy> strategys = prizePoolGroup.getDefaultPrizePool().getStrategys();
      if (strategys != null) {
        ControlStrategy newStrategy = JSON.parseObject(strategy, ControlStrategy.class);
        PrizePoolCheck.checkStrategy(game, newStrategy);
        for (int i = 0; i < strategys.size(); i++) {
          ControlStrategy item = strategys.get(i);
          if (item.getId() == strategyId) {
            strategys.set(i, newStrategy);
            roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
            break;
          }
        }
      }
    });
  }

  /**
   * 修改金币范围奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game 游戏编号.
   * @param roomId 房间编号.
   * @param poolId 奖池编号.
   * @param strategyId 策略编号.
   * @param strategy 策略内容.
   */
  public void modifyGoldRangeRoomPrizePoolStrategy(Game game, int roomId, long poolId,
      long strategyId, String strategy) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == poolId) {
            prizePool = item;
            break;
          }
        }
      }

      if (prizePool != null) {
        List<ControlStrategy> strategys = prizePool.getStrategys();
        if (strategys != null) {
          ControlStrategy newStrategy = JSON.parseObject(strategy, ControlStrategy.class);
          PrizePoolCheck.checkStrategy(game, newStrategy);
          for (int i = 0; i < strategys.size(); i++) {
            ControlStrategy item = strategys.get(i);
            if (item.getId() == strategyId) {
              strategys.set(i, newStrategy);
              roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
              break;
            }
          }
        }
      }
    });
  }

  /**
   * 新增默认房间奖池,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game 游戏编号.
   * @param roomId 房间编号.
   * @param prizePoolGolds 奖池金额.
   */
  public void addDefaultRoomPrizePool(Game game, int roomId, Long prizePoolGolds) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      if (prizePoolGroup == null) {
        prizePoolGroup = new RoomPrizePoolGroup(game.getType(), roomId);
        RoomPrizePoolGroupKey key = new RoomPrizePoolGroupKey(game.getType(), roomId);
        poolGroups.put(key, prizePoolGroup);
        roomPrizePoolGroupDao.insertPrizePoolGroup(prizePoolGroup.domain());
      }
      RoomPrizePool prizePool = new RoomPrizePool();
      prizePool.setGame(game.getType());
      prizePool.setRoom(roomId);
      prizePool.setPrize(new AtomicLong(prizePoolGolds));
      prizePool.setStrategys(new ArrayList<ControlStrategy>());
      prizePoolGroup.setDefaultPrizePool(prizePool);
      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 新增金币范围奖池,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param prizePoolGolds 奖池金币
   * @param lower 金币下限
   * @param upper 金币上限
   */
  public void addGoldRangeRoomPrizePool(Game game, int roomId, Long prizePoolGolds, Long lower,
      Long upper) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      if (prizePoolGroup == null) {
        prizePoolGroup = new RoomPrizePoolGroup(game.getType(), roomId);
        RoomPrizePoolGroupKey key = new RoomPrizePoolGroupKey(game.getType(), roomId);
        poolGroups.put(key, prizePoolGroup);
        roomPrizePoolGroupDao.insertPrizePoolGroup(prizePoolGroup.domain());
      }

      GoldRangeRoomPrizePool prizePool = new GoldRangeRoomPrizePool();
      prizePool.setGame(game.getType());
      prizePool.setRoom(roomId);
      prizePool.setId(goldPricePoolSeed.incrementAndGet());
      prizePool.setLower(lower);
      prizePool.setUpper(upper);
      prizePool.setStrategys(new ArrayList<ControlStrategy>());
      prizePool.getPrize().set(prizePoolGolds);

      prizePoolGroup.getGoldRangePrizePools().add(prizePool);
      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 新增默认奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game .
   * @param roomId .
   * @param strategy .
   */
  public void addDefaultRoomPrizePoolStrategy(Game game, int roomId, String strategy) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<ControlStrategy> strategys = prizePoolGroup.getDefaultPrizePool().getStrategys();
      if (strategys == null) {
        strategys = new ArrayList<>();
        prizePoolGroup.getDefaultPrizePool().setStrategys(strategys);
      }
      ControlStrategy strategyObj = JSON.parseObject(strategy, ControlStrategy.class);
      PrizePoolCheck.checkStrategy(game, strategyObj);

      strategyObj.setId(strategySeed.incrementAndGet());
      strategys.add(strategyObj);
      roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
    });
  }

  /**
   * 新增金币范围奖池策略,在对应游戏Executor中执行保证线程安全 .
   * 
   * @param game 游戏编号.
   * @param roomId 房间编号.
   * @param prizePoolId 奖池编号.
   * @param strategy 策略内容.
   */
  public void addGoldRangeRoomPrizePoolStrategy(Game game, int roomId, Long prizePoolId,
      String strategy) {
    executorMgr.getGameExecutor(game.getModuleId()).execute(() -> {
      RoomPrizePoolGroup prizePoolGroup = getRoomPrizePoolGroup(game, roomId);
      List<GoldRangeRoomPrizePool> prizePools = prizePoolGroup.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(prizePools)) {
        for (GoldRangeRoomPrizePool item : prizePools) {
          if (item.getId() == prizePoolId) {
            prizePool = item;
            break;
          }
        }
      }
      if (prizePool != null) {
        List<ControlStrategy> strategys = prizePool.getStrategys();
        if (strategys == null) {
          prizePool.setStrategys(new ArrayList<>());
        }
        ControlStrategy controlStrategy = JSON.parseObject(strategy, ControlStrategy.class);
        PrizePoolCheck.checkStrategy(game, controlStrategy);

        controlStrategy.setId(strategySeed.incrementAndGet());
        prizePool.getStrategys().add(controlStrategy);
        roomPrizePoolGroupDao.updatePrizePoolGroup(prizePoolGroup.domain());
      }
    });
  }

  /**
   * 根据游戏和房间获取奖池组 .
   * 
   * @param game .
   * @param roomId .
   * @return
   */
  public RoomPrizePoolGroup getRoomPrizePoolGroup(Game game, int roomId) {

    return poolGroups.get(new RoomPrizePoolGroupKey(game.getType(), roomId));
  }

  /**
   * 关服事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onShutDown(ShutdownEvent event) {
    LOG.info("保存奖池数据中...");
    saveAllRoomPoolGroups();
    LOG.info("保存奖池数据成功");
  }
}
