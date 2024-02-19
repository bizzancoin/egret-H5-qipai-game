
package com.idealighter.game.core.service.games.jcby.struct;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.buyu.Bullet;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 金蝉捕鱼seat .
 * 
 * @date 2016年3月7日 下午8:58:56
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JcbySeat extends AbstractSeat {
  // 所属桌子
  private final JcbyTable table;
  // 炮管id
  private int batteryId = 1;
  // 是否能量炮
  private boolean power = false;
  // 子弹id种子
  private long bulletSeed = 1;
  // 玩家发出的子弹，如果为负则是能量炮子弹
  private Map<Long, Bullet> bullets = new HashMap<>();
  // 玩家赢得筹码
  private long winChips = 0;
  // 子弹定时清除
  private Map<Long, ScheduledFuture<?>> bulletScheduleMap = new HashMap<Long, ScheduledFuture<?>>();

  private long betting = 0;

  private long bonus = 0;
  // 锁定鱼编号
  private int lockFish = 0;
  // 锁定角度
  private int lockAngle = -1;

  /**
   * 构造函数 .
   * 
   * @param order .
   * @param table .
   */
  public JcbySeat(int order, JcbyTable table) {
    super(order);
    this.table = table;
  }

  /**
   * 清空座位数据 .
   */
  public void clear() {
    this.playerId = 0;
    this.playerName = null;
    this.totalChips = 0;
    this.winChips = 0;
    this.batteryId = 1;
    this.power = false;
    this.bulletSeed = 1L;
    this.betting = 0;
    this.bonus = 0;
    this.bullets.clear();
    this.lockFish = 0;
    this.lockAngle = -1;
  }

  @Override
  public AbstractTable table() {
    return table;
  }
}
