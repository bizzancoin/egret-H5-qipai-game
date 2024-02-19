package com.idealighter.game.core.service.games.jcby.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.common.buyu.Bullet;
import com.idealighter.game.core.service.games.jcby.struct.JcbyDifficulty;
import com.idealighter.game.core.service.games.jcby.struct.JcbyFish;
import com.idealighter.game.core.service.games.jcby.struct.JcbyScence;
import com.idealighter.game.core.service.games.jcby.struct.JcbySeat;
import com.idealighter.game.core.service.prizepool.manager.PlayerPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.manager.RoomPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.struct.control.FishDifficulty;
import com.idealighter.game.core.service.prizepool.struct.control.JcbyControl;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePool;
import com.idealighter.game.core.tuple.Pair;
import com.idealighter.game.core.tuple.Triplet;
import com.idealighter.game.dictionary.dic.JcbyFishDic;
import com.idealighter.game.dictionary.domwrapper.JcbyFishDomWrapper;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 金蝉捕鱼控制脚本 .
 * 
 * @date 2016年4月5日 下午1:40:04
 *
 */
@Singleton
public class JcbyControlScript {

  private static final TypeReference<JcbyControl> JCBY_CONTROL_TYPE =
      new TypeReference<JcbyControl>() {};

  @Inject
  private PlayerPrizePoolMgrScript playerPrizePoolMgr;
  @Inject
  private RoomPrizePoolMgrScript roomPrizePoolMgr;
  @Inject
  private JcbyFishDic fishDic;
  @Inject
  private JcbyMgr jcbyMgr;

  /**
   * 鱼是否死亡 .
   * 
   * @param seat 座位信息.
   * @param bullet .
   * @param fish . 可以为null,比如多个玩家重复打中鱼，第一个打死，第二个子弹被吃掉,以及切场景时，客户端也在发送碰撞消息，也会出现fish为null
   * @return 死亡的鱼集合，死亡的鱼总倍数,死亡的鱼总筹码
   */
  public Triplet<LinkedHashSet<JcbyFish>, Long, Long> hitDied(JcbySeat seat, Bullet bullet,
      JcbyFish fish) {
    LinkedHashSet<JcbyFish> dieFishs = getDieFishs(fish);
    int bulletScore = bullet.power ? bullet.score * 2 : bullet.score;
    Pair<Long, Long> totalData = totalData(dieFishs, bulletScore);
    long totalFishMultiple = totalData.v1;
    long totalFishChips = totalData.v2;

    /*
     * 个人奖池控制 .
     */
    PlayerPrizePool prizePool = playerPrizePoolMgr.prizePool(seat.getPlayerId(), Game.JCBY);
    if (!seat.isRobot() && prizePool != null && prizePool.getPrize().get() != 0) {
      return new Triplet<>(
          playerPrizePoolDied(seat, prizePool, bullet, totalFishMultiple, totalFishChips) ? dieFishs
              : null,
          totalFishMultiple, totalFishChips);
    }

    /*
     * 房间奖池控制 .
     */
    int roomId = seat.getTable().getRoom().getId();
    RoomPrizePool roomPrizePool =
        roomPrizePoolMgr.roomPrizePool(seat.getPlayerId(), Game.JCBY, roomId);
    if (!seat.isRobot() && roomPrizePool != null) {
      return new Triplet<>(
          roomPrizePoolDied(seat, roomPrizePool, bullet, totalFishMultiple, totalFishChips)
              ? dieFishs
              : null,
          totalFishMultiple, totalFishChips);
    }

    // 鱼正常倍率死亡
    return new Triplet<>(RandCodeUtil.random(totalFishMultiple) < 1 ? dieFishs : null,
        totalFishMultiple, totalFishChips);
  }

  /**
   * 鱼不存在或鱼已经死了，奖池吃子弹 .
   * 
   * @param seat 座位信息.
   * @param bullet .
   */
  public void eatBullet(JcbySeat seat, Bullet bullet) {
    /*
     * 个人奖池控制 .
     */
    PlayerPrizePool prizePool = playerPrizePoolMgr.prizePool(seat.getPlayerId(), Game.JCBY);
    if (!seat.isRobot() && prizePool != null && prizePool.getPrize().get() != 0) {
      addPlayerPrizePool(seat, prizePool, -bullet.score);
      return;
    }

    /*
     * 房间奖池控制 .
     */
    int roomId = seat.getTable().getRoom().getId();
    RoomPrizePool roomPrizePool =
        roomPrizePoolMgr.roomPrizePool(seat.getPlayerId(), Game.JCBY, roomId);
    if (!seat.isRobot() && roomPrizePool != null) {
      addRoomPrizePool(seat, roomPrizePool, bullet.score);
      return;
    }
  }

  /**
   * 玩家奖池控制鱼死亡,鱼的倍数* . 10000/10000,分子是难度系数,10000不输不赢,默认难度系数
   * 
   * @param seat 座位信息.
   * @param prizePool .
   * @param bullet .
   * @param totalFishMultiple . 合计鱼倍数
   * @param totalFishChips . 合计鱼分数
   * @return
   */
  public boolean playerPrizePoolDied(JcbySeat seat, PlayerPrizePool prizePool, Bullet bullet,
      long totalFishMultiple, long totalFishChips) {
    JcbyControl control = JSON.parseObject(prizePool.getControl(), JCBY_CONTROL_TYPE);
    // 鱼是否死亡
    boolean died = RandCodeUtil.random(totalFishMultiple) < 1;
    for (FishDifficulty difficulty : control.getDifficultys()) {
      int multipleLower = difficulty.getMultipleLower();
      int multipleUpper = difficulty.getMultipleUpper();
      JcbyDifficulty jcbyDiff = JcbyDifficulty.get(difficulty.getDifficulty());
      int difficultyLower = jcbyDiff.getLower();
      int difficultyUpper = jcbyDiff.getUpper();

      if (totalFishMultiple <= multipleUpper && totalFishMultiple >= multipleLower) {
        died = RandCodeUtil.random(totalFishMultiple * RandCodeUtil.random(difficultyLower,
            difficultyUpper)) < JcbyControl.DEFAULT_DIFFICULTY;
        break;
      }
    }

    long winChips = 0;
    if (!died) {
      winChips = -bullet.score;
    } else {
      winChips = totalFishChips - bullet.score;
    }

    addPlayerPrizePool(seat, prizePool, winChips);

    return died;
  }

  /**
   * 房间奖池控制 .
   * 
   * @param seat 座位信息.
   * @param roomPrizePool .
   * @param bullet .
   * @param totalFishMultiple .
   * @param totalFishChips .
   * @return
   */
  public boolean roomPrizePoolDied(JcbySeat seat, RoomPrizePool roomPrizePool, Bullet bullet,
      long totalFishMultiple, long totalFishChips) {
    // 房间奖池控制策略
    ControlStrategy stgy = roomPrizePool.abledControlStrategy();
    // 鱼是否死亡
    boolean died = RandCodeUtil.random(totalFishMultiple) < 1;
    if (stgy != null) {
      JcbyControl control = stgy.control(JCBY_CONTROL_TYPE);
      for (FishDifficulty difficulty : control.getDifficultys()) {
        int multipleLower = difficulty.getMultipleLower();
        int multipleUpper = difficulty.getMultipleUpper();
        JcbyDifficulty jcbyDiff = JcbyDifficulty.get(difficulty.getDifficulty());
        int difficultyLower = jcbyDiff.getLower();
        int difficultyUpper = jcbyDiff.getUpper();

        if (totalFishMultiple <= multipleUpper && totalFishMultiple >= multipleLower) {
          died = RandCodeUtil.random(totalFishMultiple * RandCodeUtil.random(difficultyLower,
              difficultyUpper)) < JcbyControl.DEFAULT_DIFFICULTY;
          break;
        }
      }
    }

    long winChips = 0;
    if (!died) {
      winChips = -bullet.score;
    } else {
      winChips = totalFishChips - bullet.score;
    }

    addRoomPrizePool(seat, roomPrizePool, -winChips);
    return died;
  }

  /**
   * 增加奖池 .
   * 
   * @param seat 座位信息.
   * @param prizePool .
   * @param chips .
   */
  private void addPlayerPrizePool(JcbySeat seat, PlayerPrizePool prizePool, long chips) {
    long prize = prizePool.getPrize().get();
    long winGold = jcbyMgr.convertGold(seat.getTable().getRoom().getRoomDomain(), chips);

    if (prize > 0) {
      prize = prize - winGold;
      prize = prize < 0 ? 0 : prize;
    } else if (prize < 0) {
      prize = prize - winGold;
      prize = prize > 0 ? 0 : prize;
    }

    prizePool.getPrize().getAndSet(prize);
  }

  /**
   * 增加房间奖池 .
   * 
   * @param seat 座位信息.
   * @param roomPrizePool .
   * @param chips .
   */
  private void addRoomPrizePool(JcbySeat seat, RoomPrizePool roomPrizePool, long chips) {

    roomPrizePool.getPrize()
        .addAndGet(jcbyMgr.convertGold(seat.getTable().getRoom().getRoomDomain(), chips));
  }

  /**
   * 获取鱼死亡和由它引起的死亡的鱼 .
   * 
   * @param fish .
   * @return 本鱼死亡时返回的所有因它死亡的鱼(包括自己)
   */
  private LinkedHashSet<JcbyFish> getDieFishs(JcbyFish fish) {
    JcbyScence scence = fish.getScence();
    Map<Integer, JcbyFish> fishs = scence.getFishs();
    // 被打死鱼的唯一标识
    int id = fish.getId();
    JcbyFishDomWrapper fishDom = fishDic.get(fish.getFishId());
    int type = fishDom.getType();
    // 死亡的鱼
    LinkedHashSet<JcbyFish> dieFishs = new LinkedHashSet<>();
    dieFishs.add(fish);

    // KINDS_BOMB_FIS和FULL_SCREEN_FISH不会递归死鱼，以后如果添加范围炸弹鱼死后会递归死鱼
    if (type == JcbyMgr.KINDS_BOMB_FISH) {
      for (JcbyFish f : fishs.values()) {
        if (f.getId() != id && f.alive() && fishDom.getFishsData().contains(f.getFishId())) {
          dieFishs.add(f);
        }
      }
    } else if (type == JcbyMgr.FULL_SCREEN_FISH) {
      for (JcbyFish f : fishs.values()) {
        if (f.getId() != id && f.alive()) {
          dieFishs.add(f);
        }
      }
    }

    return dieFishs;
  }

  /**
   * 鱼的合计倍数 .
   * 
   * @param dieFishs .
   * @param bulletScore .
   * @return 返回合计的鱼的倍数和合计分数
   */
  private Pair<Long, Long> totalData(Set<JcbyFish> dieFishs, int bulletScore) {
    // 鱼的合计死亡判断倍数
    long totalMultiple = 0;
    // 合计打死鱼获得的分数(筹码)
    long totalChips = 0;
    for (JcbyFish fish : dieFishs) {
      totalMultiple += fish.getMultiple();
      totalChips += fish.getMultiple() * bulletScore;
    }

    return new Pair<>(totalMultiple, totalChips);
  }

}



