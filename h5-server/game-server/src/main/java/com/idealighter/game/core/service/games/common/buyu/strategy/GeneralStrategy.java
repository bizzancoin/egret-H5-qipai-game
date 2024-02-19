
package com.idealighter.game.core.service.games.common.buyu.strategy;

import com.idealighter.game.core.service.games.common.buyu.Deviate;
import com.idealighter.game.core.service.games.common.buyu.Position;

import java.util.List;

/**
 * 普通刷鱼策略 .
 * 
 * @date 2016年3月8日 下午4:08:45
 *
 */
public class GeneralStrategy implements Strategy {
  // 初始延迟(秒)
  private int initDelay;
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
  private boolean sameFish;
  // 每次刷出的鱼的路径是否一样
  private boolean sameRoad;
  // 路线
  private List<Integer> roads;
  // 刷鱼起点
  private Position start;
  // 刷鱼起点x方向偏差
  private Deviate deviateX;
  // 刷鱼起点y方向偏差
  private Deviate deviateY;

  @Override
  public StrategyType type() {
    return StrategyType.GENERAL;
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
   * 权重.
   * 
   * @param weights 权重.
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

  public Deviate getDeviateX() {
    return deviateX;
  }

  public void setDeviateX(Deviate deviateX) {
    this.deviateX = deviateX;
  }

  public Deviate getDeviateY() {
    return deviateY;
  }

  public void setDeviateY(Deviate deviateY) {
    this.deviateY = deviateY;
  }

  public Integer getTotalWeight() {
    return totalWeight;
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

  public boolean isSameFish() {
    return sameFish;
  }

  public void setSameFish(boolean sameFish) {
    this.sameFish = sameFish;
  }

  public boolean isSameRoad() {
    return sameRoad;
  }

  public void setSameRoad(boolean sameRoad) {
    this.sameRoad = sameRoad;
  }
}
