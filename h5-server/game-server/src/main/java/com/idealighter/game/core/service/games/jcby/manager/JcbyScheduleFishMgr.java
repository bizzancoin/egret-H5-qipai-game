
package com.idealighter.game.core.service.games.jcby.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.common.buyu.Deviate;
import com.idealighter.game.core.service.games.common.buyu.Position;
import com.idealighter.game.core.service.games.common.buyu.Road;
import com.idealighter.game.core.service.games.common.buyu.strategy.CircleStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.GeneralStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.LineStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.PointStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.RowStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.ShoalStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.SpreadStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.Strategy;
import com.idealighter.game.core.service.games.jcby.struct.JcbyFish;
import com.idealighter.game.core.service.games.jcby.struct.JcbyScence;
import com.idealighter.game.dictionary.dic.JcbyFishDic;
import com.idealighter.game.dictionary.domain.JcbyFishDomain;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 金蝉捕鱼ScheduleFish,调度鱼的产生 .
 * 
 * @date 2016年3月11日 上午9:22:12
 *
 */
@Singleton
public class JcbyScheduleFishMgr {

  @Inject
  private JcbyDataMgr dataMgr;
  @Inject
  private JcbyFishDic fishDic;
  @Inject
  private JcbyMsgMgr msgMgr;
  @Inject
  private ScheduleMgr scheduleMgr;

  // 游戏线程executor,可以执行普通任务和定时任务
  public final DisruptorExecutor gameExecutor;

  @Inject
  public JcbyScheduleFishMgr(ExecutorMgr executorMgr) {
    this.gameExecutor = executorMgr.getGameExecutor(Game.JCBY.getModuleId());
  }

  /**
   * schedule刷鱼策略 .
   * 
   * @param scence .
   * @param stgy .
   * @param spreadStart . 动态点扩散出圈鱼(SpreadStrategy)的起点,如鱼死亡时
   */
  public void scheduleProduceFishs(JcbyScence scence, Strategy stgy, Position spreadStart) {
    List<ScheduledFuture<?>> produceFishFutures = scence.getProduceFishFutures();
    switch (stgy.type()) {
      case CIRCLE:
        produceFishFutures.add(scheduleProduceCircleFishs(scence, (CircleStrategy) stgy));
        break;
      case SPREAD:
        produceFishFutures
            .add(scheduleProduceSpreadFishs(scence, (SpreadStrategy) stgy, spreadStart));
        break;
      case GENERAL:
        produceFishFutures.add(scheduleProduceGeneralFishs(scence, (GeneralStrategy) stgy));
        break;
      case LINE:
        produceFishFutures.add(scheduleProduceLineFishs(scence, (LineStrategy) stgy));
        break;
      case POINT:
        produceFishFutures.add(scheduleProducePointFishs(scence, (PointStrategy) stgy));
        break;
      case ROW:
        produceFishFutures.add(scheduleProduceRowFish(scence, (RowStrategy) stgy));
        break;
      case SHOAL:
        ShoalStrategy shoalStgy = (ShoalStrategy) stgy;
        for (Integer stgyId : shoalStgy.getStrategys()) {
          // 鱼阵递归刷鱼
          scheduleProduceFishs(scence, dataMgr.getStrategy(stgyId), null);
        }
        break;
      default:
        break;
    }
  }

  /**
   * 普通刷鱼 .
   * 
   * @param scence .
   * @param stgy .
   */
  private ScheduledFuture<?> scheduleProduceGeneralFishs(JcbyScence scence, GeneralStrategy stgy) {
    return scheduleMgr.scheduleWithFixedTime(() -> {
      List<JcbyFish> fishs = new ArrayList<>();
      // 鱼的出生时间
      long birthTime = System.currentTimeMillis();
      int fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
      int roadId = RandCodeUtil.randomList(stgy.getRoads());

      int fishNum = RandCodeUtil.random(stgy.getMinNum(), stgy.getMaxNum());
      for (int i = 0; i < fishNum; i++) {
        if (!stgy.isSameFish()) {
          fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
        }
        if (!stgy.isSameRoad()) {
          roadId = RandCodeUtil.randomList(stgy.getRoads());
        }

        Road road = dataMgr.getRoad(roadId);
        JcbyFishDomain fishDom = fishDic.get(fishId);
        int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());
        Position start = randomStart(stgy.getStart(), stgy.getDeviateX(), stgy.getDeviateY());
        fishs.add(JcbyFish.create(fishId, multiple, birthTime, scence, start, road));
      }

      msgMgr.sendProduceFishMsg(scence.getTable(), fishs);
      for (JcbyFish fish : fishs) {
        scence.getFishs().put(fish.getId(), fish);
      }
    }, stgy.getInitDelay(), stgy.getDelay(), stgy.getTimes(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * 点刷鱼 .
   * 
   * @param scence .
   * @param stgy .
   */
  private ScheduledFuture<?> scheduleProducePointFishs(JcbyScence scence, PointStrategy stgy) {
    return scheduleMgr.scheduleWithFixedTime(() -> {
      List<JcbyFish> fishs = new ArrayList<>();
      // 鱼的出生时间
      long birthTime = System.currentTimeMillis();
      int fishType = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
      int roadId = RandCodeUtil.randomList(stgy.getRoads());
      Road road = dataMgr.getRoad(roadId);
      JcbyFishDomain fishDom = fishDic.get(fishType);
      int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());
      Position start = stgy.getPos();
      fishs.add(JcbyFish.create(fishType, multiple, birthTime, scence, new Position(start), road));

      msgMgr.sendProduceFishMsg(scence.getTable(), fishs);
      for (JcbyFish fish : fishs) {
        scence.getFishs().put(fish.getId(), fish);
      }
    }, stgy.getInitDelay(), stgy.getDelay(), stgy.getTimes(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * 线刷鱼 .
   * 
   * @param scence .
   * @param stgy .
   */
  private ScheduledFuture<?> scheduleProduceLineFishs(JcbyScence scence, LineStrategy stgy) {
    return scheduleMgr.schedule(() -> {
      List<JcbyFish> fishs = new ArrayList<>();
      // 鱼的出生时间
      long birthTime = System.currentTimeMillis();
      Position start = stgy.getStart();
      Position end = stgy.getEnd();
      int fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());

      double fishNum = RandCodeUtil.random(stgy.getMinNum(), stgy.getMaxNum());
      double avgX = (end.xcoord - start.xcoord) / fishNum;
      double avgY = (end.ycoord - start.ycoord) / fishNum;

      for (int i = 0; i < fishNum; i++) {
        if (!stgy.isSame()) {
          fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
        }
        JcbyFishDomain fishDom = fishDic.get(fishId);
        int roadId = RandCodeUtil.randomList(stgy.getRoads());
        Road road = dataMgr.getRoad(roadId);
        int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());

        fishs.add(JcbyFish.create(fishId, multiple, birthTime, scence,
            new Position((int) (start.xcoord + avgX * i), (int) (start.ycoord + avgY * i)), road));
      }

      msgMgr.sendProduceFishMsg(scence.getTable(), fishs);
      for (JcbyFish fish : fishs) {
        scence.getFishs().put(fish.getId(), fish);
      }
    }, stgy.getInitDelay(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * schedule产生圆鱼 .
   * 
   * @param scence .
   * @param stgy .
   */
  private ScheduledFuture<?> scheduleProduceCircleFishs(JcbyScence scence, CircleStrategy stgy) {
    return scheduleMgr.schedule(() -> {
      List<JcbyFish> fishs = new ArrayList<>();
      // 鱼的出生时间
      long birthTime = System.currentTimeMillis();
      Position center = stgy.getCenter();
      int r = stgy.getRadius();
      int roadId = RandCodeUtil.randomList(stgy.getRoads());
      Road road = dataMgr.getRoad(roadId);
      int fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());

      int fishNum = RandCodeUtil.random(stgy.getMinNum(), stgy.getMaxNum());
      double avgAngle = 360 / fishNum;
      for (int i = 0; i < fishNum; i++) {
        if (!stgy.isSame()) {
          fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
        }
        JcbyFishDomain fishDom = fishDic.get(fishId);
        int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());
        double angle = avgAngle * i;
        Position start = new Position((int) (center.xcoord + Math.cos(Math.toRadians(angle)) * r),
            (int) (center.ycoord + Math.sin(Math.toRadians(angle)) * r));
        fishs.add(JcbyFish.create(fishId, multiple, birthTime, scence, start, road));
      }

      msgMgr.sendProduceFishMsg(scence.getTable(), fishs);
      for (JcbyFish fish : fishs) {
        scence.getFishs().put(fish.getId(), fish);
      }
    }, stgy.getInitDelay(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * 鱼排刷鱼 .
   * 
   * @param scence .
   * @param stgy .
   */
  private ScheduledFuture<?> scheduleProduceRowFish(JcbyScence scence, RowStrategy stgy) {
    return scheduleMgr.scheduleWithFixedTime(() -> {
      int roadId = RandCodeUtil.randomList(stgy.getRoads());
      Road road = dataMgr.getRoad(roadId);
      int fishType = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
      JcbyFishDomain fishDom = fishDic.get(fishType);
      int fishNum = RandCodeUtil.random(stgy.getMinNum(), stgy.getMaxNum());

      for (int i = 0; i < fishNum; i++) {
        ScheduledFuture<?> future = scheduleMgr.schedule(() -> {
          // 鱼的出生时间
          long birthTime = System.currentTimeMillis();
          int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());
          JcbyFish fish = JcbyFish.create(fishType, multiple, birthTime, scence,
              new Position(stgy.getStart()), road);
          msgMgr.sendProduceFishMsg(scence.getTable(), Arrays.asList(fish));
          scence.getFishs().put(fish.getId(), fish);
        }, stgy.getFishDelay() * i, TimeUnit.MILLISECONDS, gameExecutor);

        scence.getProduceFishFutures().add(future);
      }
    }, stgy.getInitDelay(), stgy.getDelay(), stgy.getTimes(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * 指定起点扩散出鱼 .
   * 
   * @param scence .
   * @param stgy .
   * @param deathPos .
   */
  private ScheduledFuture<?> scheduleProduceSpreadFishs(JcbyScence scence, SpreadStrategy stgy,
      Position start) {
    return scheduleMgr.scheduleWithFixedTime(() -> {
      List<JcbyFish> fishs = new ArrayList<>();
      // 鱼的出生时间
      long birthTime = System.currentTimeMillis();
      int fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
      int roadId = RandCodeUtil.randomList(stgy.getRoads());
      Road road = dataMgr.getRoad(roadId);
      double fishNum = RandCodeUtil.random(stgy.getMinNum(), stgy.getMaxNum());
      double avgAngle = 360 / fishNum;
      for (int i = 0; i < fishNum; i++) {
        if (!stgy.isSame()) {
          fishId = randomFish(stgy.getFishs(), stgy.getWeights(), stgy.getTotalWeight());
        }
        JcbyFishDomain fishDom = fishDic.get(fishId);
        int multiple = RandCodeUtil.random(fishDom.getMinRate(), fishDom.getMaxRate());
        double angle = avgAngle * i;
        fishs.add(JcbyFish.create(fishId, multiple, birthTime, scence, new Position(start), road,
            (int) angle));
      }

      msgMgr.sendProduceFishMsg(scence.getTable(), fishs);
      for (JcbyFish fish : fishs) {
        scence.getFishs().put(fish.getId(), fish);
      }
    }, 0, stgy.getDelay(), stgy.getTimes(), TimeUnit.MILLISECONDS, gameExecutor);
  }

  /**
   * 随机鱼 .
   * 
   * @param fishs .
   * @param weights .
   * @param totalWeight .
   * @return
   */
  private static int randomFish(List<Integer> fishs, List<Integer> weights, int totalWeight) {
    int fishId = 0;
    // 随机数
    int randWeight = RandCodeUtil.random(totalWeight);
    int sum = 0;

    for (int i = 0; i < weights.size(); i++) {
      sum += weights.get(i);
      if (randWeight < sum) {
        fishId = fishs.get(i);
        break;
      }
    }

    return fishId;
  }

  /**
   * 随机起点 .
   * 
   * @param strategy .
   * @return
   */
  private static Position randomStart(Position start, Deviate deviateX, Deviate deviateY) {
    double x = start.xcoord + RandCodeUtil.random(deviateX.getMin(), deviateX.getMax());
    double y = start.ycoord + RandCodeUtil.random(deviateY.getMin(), deviateY.getMax());

    return new Position(x, y);
  }

}
