
package com.idealighter.game.core.service.games.common.buyu.strategy;

import com.idealighter.game.core.service.games.common.buyu.Position;

import java.util.List;

/**
 * 刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class RowStrategy implements Strategy {
  // 初始延迟(秒)
  private int initDelay;
  // 刷鱼间隔(秒)
  private int delay;
  // 刷鱼次数
  private int times;
  // 鱼间隔(秒)
  private int fishDelay;
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
  // 路线
  private List<Integer> roads;
  // 刷鱼起点
  private Position start;

  @Override
  public StrategyType type() {
    return StrategyType.ROW;
  }

  public int getInitDelay() {
    return initDelay;
  }

  public void setInitDelay(int initDelay) {
    this.initDelay = initDelay;
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

  public Position getStart() {
    return start;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public Integer getTotalWeight() {
    return totalWeight;
  }

  public void setTotalWeight(Integer totalWeight) {
    this.totalWeight = totalWeight;
  }

  public List<Integer> getRoads() {
    return roads;
  }

  public void setRoads(List<Integer> roads) {
    this.roads = roads;
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

  public int getFishDelay() {
    return fishDelay;
  }

  public void setFishDelay(int fishDelay) {
    this.fishDelay = fishDelay;
  }
}
