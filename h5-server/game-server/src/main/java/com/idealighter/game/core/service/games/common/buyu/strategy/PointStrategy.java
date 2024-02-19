
package com.idealighter.game.core.service.games.common.buyu.strategy;

import com.idealighter.game.core.service.games.common.buyu.Position;

import java.util.List;

/**
 * 点刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class PointStrategy implements Strategy {
  // 初始延迟(秒)
  private int initDelay;
  // 刷鱼间隔(秒)
  private int delay;
  // 次数
  private int times;
  // 点位置
  private Position pos;
  // 鱼的种类
  private List<Integer> fishs;
  // 鱼的权重
  private List<Integer> weights;
  // 鱼的权重和
  private int totalWeight;
  // 路线
  private List<Integer> roads;

  @Override
  public StrategyType type() {
    return StrategyType.POINT;
  }

  public int getInitDelay() {
    return initDelay;
  }

  public void setInitDelay(int initDelay) {
    this.initDelay = initDelay;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  public int getTimes() {
    return times;
  }

  public void setTimes(int times) {
    this.times = times;
  }

  public Position getPos() {
    return pos;
  }

  public void setPos(Position pos) {
    this.pos = pos;
  }

  public List<Integer> getFishs() {
    return fishs;
  }

  public void setFishs(List<Integer> fishs) {
    this.fishs = fishs;
  }

  public List<Integer> getWeights() {
    return weights;
  }

  /**
   * 权重列表.
   * 
   * @param weights 群众列表.
   */
  public void setWeights(List<Integer> weights) {
    this.weights = weights;
    int totalWeight = 0;
    for (Integer weight : weights) {
      totalWeight += weight;
    }
    this.totalWeight = totalWeight;
  }

  public int getTotalWeight() {
    return totalWeight;
  }

  public List<Integer> getRoads() {
    return roads;
  }

  public void setRoads(List<Integer> roads) {
    this.roads = roads;
  }

}
