
package com.idealighter.game.core.service.prizepool.struct.room;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.idealighter.game.core.dao.generate.domain.RoomPrizePoolGoupDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间奖池group .
 * 
 * @date 2015年10月16日 上午10:20:51
 *
 */
public class RoomPrizePoolGroup extends RoomPrizePoolGroupKey {
  // 默认奖池
  protected RoomPrizePool defaultPrizePool;
  // 金币范围奖池
  protected List<GoldRangeRoomPrizePool> goldRangePrizePools = new ArrayList<>();

  public RoomPrizePoolGroup() {}

  /**
   * 构造函数 .
   * 
   * @param game .
   * @param roomId .
   */
  public RoomPrizePoolGroup(Integer game, Integer roomId) {
    super(game, roomId);
  }

  /**
   * 构造函数 .
   * 
   * @param domain .
   */
  public RoomPrizePoolGroup(RoomPrizePoolGoupDomain domain) {
    this.setGame(domain.getGame());
    this.setRoomId(domain.getRoomId());
    this.defaultPrizePool = JSON.parseObject(domain.getDefaultPrizePool(), RoomPrizePool.class);
    this.goldRangePrizePools = JSON.parseObject(domain.getPrizePools(),
        new TypeReference<List<GoldRangeRoomPrizePool>>() {});
  }

  /**
   * 获取用于持久化的domain .
   * 
   * @return
   */
  public RoomPrizePoolGoupDomain domain() {
    RoomPrizePoolGoupDomain domain = new RoomPrizePoolGoupDomain();
    domain.setGame(this.getGame());
    domain.setRoomId(this.getRoomId());
    domain.setDefaultPrizePool(JSON.toJSONString(this.defaultPrizePool));
    domain.setPrizePools(JSON.toJSONString(this.goldRangePrizePools));
    return domain;
  }

  public RoomPrizePool getDefaultPrizePool() {
    return defaultPrizePool;
  }

  public void setDefaultPrizePool(RoomPrizePool defaultPrizePool) {
    this.defaultPrizePool = defaultPrizePool;
  }

  public List<GoldRangeRoomPrizePool> getGoldRangePrizePools() {
    return goldRangePrizePools;
  }

  public void setGoldRangePrizePools(List<GoldRangeRoomPrizePool> goldRangePrizePools) {
    this.goldRangePrizePools = goldRangePrizePools;
  }

}
