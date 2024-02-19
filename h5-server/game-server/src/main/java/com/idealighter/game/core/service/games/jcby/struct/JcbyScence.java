package com.idealighter.game.core.service.games.jcby.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 捕鱼场景 .
 * 
 * @date 2016年3月8日 下午4:08:30
 *
 */
@Data
@EqualsAndHashCode(exclude = "table")
public class JcbyScence {
  // 场景id
  private final int id;
  // public
  private final JcbyTable table;
  // 鱼
  private final Map<Integer, JcbyFish> fishs = new HashMap<>();
  // 产生鱼的ScheduledFuture
  private final List<ScheduledFuture<?>> produceFishFutures = new ArrayList<>(1000);
  // 本场景鱼的id生成种子
  private int fishIdSeed = 0;

  /**
   * 构造函数 .
   * 
   * @param id .
   * @param table .
   */
  public JcbyScence(int id, JcbyTable table) {
    super();
    this.id = id;
    this.table = table;
  }

  /**
   * 重置场景 .
   */
  public void reset() {
    for (ScheduledFuture<?> future : produceFishFutures) {
      future.cancel(false);
    }
    produceFishFutures.clear();
    this.fishs.clear();
    this.fishIdSeed = id * 1000000;
  }
}
