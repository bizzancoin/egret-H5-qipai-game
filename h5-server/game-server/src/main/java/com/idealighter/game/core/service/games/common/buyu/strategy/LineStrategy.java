
package com.idealighter.game.core.service.games.common.buyu.strategy;

import com.idealighter.game.core.service.games.common.buyu.Position;

import java.util.List;

/**
 * 线刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class LineStrategy implements Strategy {
  // 初始延迟(秒)
  private int initDelay;
  // 线起点
  private Position start;
  // 线终点位置
  private Position end;
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
    return StrategyType.LINE;
  }

  public int getInitDelay() {
    return initDelay;
  }

  public void setInitDelay(int initDelay) {
    this.initDelay = initDelay;
  }

  public Position getStart() {
    return start;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public Position getEnd() {
    return end;
  }

  public void setEnd(Position end) {
    this.end = end;
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
   * 权重就算.
   * 
   * @param weights 权重列表.
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
