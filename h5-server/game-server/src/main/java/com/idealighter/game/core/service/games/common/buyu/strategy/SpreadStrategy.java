
package com.idealighter.game.core.service.games.common.buyu.strategy;

import java.util.List;

/**
 * 扩散型刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class SpreadStrategy implements Strategy {
  // 刷鱼间隔(秒)
  private int delay;
  // 次数
  private int times;
  // 最小数数量
  private int minNum;
  // 最大数数量
  private int maxNum;
  // 鱼的种类
  private List<Integer> fishs;
  // 鱼的权重
  private List<Integer> weights;
  // 鱼的权重和
  private int totalWeight;
  // 每次刷出的鱼是否一样
  private boolean same;
  // 路线
  private List<Integer> roads;

  @Override
  public StrategyType type() {
    return StrategyType.SPREAD;
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

  public int getMinNum() {
    return minNum;
  }

  public void setMinNum(int minNum) {
    this.minNum = minNum;
  }

  public int getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(int maxNum) {
    this.maxNum = maxNum;
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
   * .
   * 
   * @param weights .
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

  public boolean isSame() {
    return same;
  }

  public void setSame(boolean same) {
    this.same = same;
  }

  public List<Integer> getRoads() {
    return roads;
  }

  public void setRoads(List<Integer> roads) {
    this.roads = roads;
  }
}
